package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.actions.HighLevelAction
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.language.LanguageInfo
import java.util.*

class StatementsActionsGroupingTraversal(private val action: Action, private val context: HighLevelDiff, private val languageInfo: LanguageInfo) {

    private val root: ITree = action.node
    private var isPartial = false
    private var composingIsPartial = false
    private val baseActions = TreeSet<Action>()
    private val composingActions = TreeSet<Action>()

    fun groupActions(): HighLevelAction {
        baseActions.add(action)
        traverse(root)

        baseActions.forEach { context.markUsed(it) }

        val highLevelAction: HighLevelAction = if (isPartial or composingIsPartial) {
            HighLevelAction.of(action, true)
        } else {
            composingActions.forEach { context.markUsed(it) }
            HighLevelAction.of(action)
        }

        return highLevelAction
    }

    private fun traverse(node: ITree) {
        if (context.mappings.isDstMapped(node)) { // the node is not newly added or deleted
            // stop the traversal on this node
            if (languageInfo.isBaseElement(node, root))
                isPartial = true
            else
                composingIsPartial = true
        } else { // the node is newly added or deleted
            val nodeAction: Action = action // the action adding this node
            assert(nodeAction.node == node)

            // group this action
            if (languageInfo.isBaseElement(node, root))
                baseActions.add(nodeAction)
            else
                composingActions.add(nodeAction)

            node.children.forEach { traverse(it) }
        }

    }
}