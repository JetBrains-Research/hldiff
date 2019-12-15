package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.actions.HighLevelAction
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.language.LanguageInfo
import kotlin.collections.LinkedHashSet

class StatementsActionsGroupingTraversal(private val action: Action, private val highLevelDiff: HighLevelDiff) {

    private val root: ITree = action.node
    private var isPartial = false
    private var composingIsPartial = false
    private val baseActions = LinkedHashSet<Action>()
    private val composingActions = LinkedHashSet<Action>()
    private val languageInfo = highLevelDiff.languageInfo

    fun groupActions(): HighLevelAction {
        baseActions.add(action)

        root.children.forEach { traverseBaseElements(it) }
        baseActions.forEach { highLevelDiff.markUsed(it) }

        val highLevelAction: HighLevelAction = if (isPartial or composingIsPartial) {
            HighLevelAction.of(action, highLevelDiff.languageInfo, true)
        } else {
            composingActions.forEach { highLevelDiff.markUsed(it) }
            HighLevelAction.of(action, highLevelDiff.languageInfo)
        }

        return highLevelAction
    }


    private fun getNodeAction(node: ITree): Action {
        return highLevelDiff.lowLevelEditScript.find { it.node == node } ?: throw RuntimeException("Action for the node not found.")
    }

    private fun traverseBaseElements(node: ITree) {
        if (languageInfo.isDeclarationOrStatement(node) && languageInfo.isComposingElement(node, root))
            traverseComposingElements(node)

        if (highLevelDiff.mappings.hasDst(node) || highLevelDiff.mappings.hasSrc(node)) {
            isPartial = true // stop the traversal on this node
        } else {
            val nodeAction = getNodeAction(node) // the action adding this node
            baseActions.add(nodeAction) // group this action
            node.children.forEach { traverseBaseElements(it) }
        }
    }

    private fun traverseComposingElements(node: ITree) {
        if (highLevelDiff.mappings.hasDst(node) || highLevelDiff.mappings.hasSrc(node)) {
            composingIsPartial = true
        } else {
            val nodeAction = getNodeAction(node)
            composingActions.add(nodeAction)
            node.children.forEach { traverseComposingElements(it) }
        }

    }
}