package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.PipelineStep
import java.util.*


fun ITree.isComplexStatment(): Boolean {
    TODO()
}

class StatementsTraversal : TopDownASTTraversal() {

    private var isPartial = false
    private val actions = TreeSet<Action>()

    override fun nodeHandler(node: ITree, context: HighLevelDiff) {
        if (!context.mappings.isDstMapped(node)) {
            isPartial = true
        } else {
            val addAction = actions.find { it.node == node }
            if (addAction != null) {
                actions.add(addAction)
            } else {
                throw RuntimeException("No add action found for the node $node")
            }
            TODO()
        }
    }
}

class StatementAdditionExtractionStep : PipelineStep<HighLevelDiff>() {

    override fun processData(payload: HighLevelDiff): HighLevelDiff {
        val diff = payload
        val actions = TreeSet<Action>()

        diff.lowLevelEditScript.forEach {
            actions.add(it)
        }

        val traversal = StatementsTraversal()

        actions.forEach {
            if (it.node.isComplexStatment()) {
                traversal.traverse(it.node, diff)
            }
        }

        return diff
    }
}