package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.actions.model.Insert
import com.github.gumtreediff.actions.model.Update
import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.actions.HighLevelAction
import ru.karvozavr.hldiff.actions.UpdateAction
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.PipelineStep

class NonStatementActionsGroupingStep : PipelineStep<HighLevelDiff>() {

    private val groups = mutableMapOf<ITree, MutableList<Action>>()
    private lateinit var highLevelDiff: HighLevelDiff

    override fun processData(payload: HighLevelDiff): HighLevelDiff {
        highLevelDiff = payload
        // filter out Updates on non-leaves
        val lowLevelEditScript = payload.lowLevelEditScript.filter {
            if (it is Update && (it.node.children != null && it.node.children.isNotEmpty())) {
                payload.markUsed(it)
                false
            } else {
                true
            }
        }

        lowLevelEditScript.forEach {
            group(it)
        }

        groups.forEach { (node, actions) -> createAction(node, actions) }

        val restActions = payload.lowLevelEditScript.filter { !payload.isUsed(it) }

        return payload.copy(lowLevelEditScript = restActions)
    }

    private fun createAction(node: ITree, actions: MutableList<Action>) {
        if (actions.size == 1) {
            val action = HighLevelAction.of(actions[0], highLevelDiff.languageInfo)
            highLevelDiff.highLevelEditScript.add(action)
        } else {
            val action = UpdateAction(node, highLevelDiff.languageInfo.getTypeName(node), actions)
            highLevelDiff.highLevelEditScript.add(action)
        }
    }

    private fun group(action: Action) {
        val ancestorStatementNode = when (action) {
            is Insert -> highLevelDiff.mappings.getSrc(getStatementAncestor(action.node))
            else -> getStatementAncestor(action.node)
        }

        groups[ancestorStatementNode]?.add(action) ?: groups.put(ancestorStatementNode, mutableListOf(action))
        highLevelDiff.markUsed(action)
    }

    private fun getStatementAncestor(node: ITree): ITree {
        var ancestor = node.parent
        while (!highLevelDiff.languageInfo.isDeclarationOrStatement(ancestor)) {
            ancestor = ancestor.parent ?: break
        }
        return ancestor
    }
}
