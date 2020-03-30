package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Delete
import com.github.gumtreediff.actions.model.Insert
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.PipelineStep


class StatementActionsGroupingStep : PipelineStep<HighLevelDiff>() {

    override fun processData(payload: HighLevelDiff): HighLevelDiff {
        val languageInfo = payload.languageInfo

        payload.lowLevelEditScript
                .filter { it is Insert || it is Delete }
                .sortedBy { it.node.depth } // sort from root to leaves
                .forEach {
            if (languageInfo.isDeclarationOrStatement(it.node) && !payload.isUsed(it)) {
                val action = StatementsActionsGroupingTraversal(it, payload).groupActions()
                payload.highLevelEditScript.add(action)
            }
        }

        val restActions = payload.lowLevelEditScript.filter { !payload.isUsed(it) }

        return payload.copy(lowLevelEditScript = restActions)
    }
}