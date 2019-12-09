package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.actions.model.Delete
import com.github.gumtreediff.actions.model.Insert
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.language.LanguageInfo
import ru.karvozavr.hldiff.pipeline.PipelineStep


class StatementActionsExtractionStep() : PipelineStep<HighLevelDiff>() {

    override fun processData(payload: HighLevelDiff): HighLevelDiff {
        val languageInfo = payload.languageInfo

        payload.lowLevelEditScript.forEach {
            val isInsertOrDelete = it is Insert || it is Delete
            if (isInsertOrDelete && languageInfo.isDeclarationOrStatement(it.node) && !payload.isUsed(it)) {
                val action = StatementsActionsGroupingTraversal(it, payload).groupActions()
                payload.highLevelEditScript.add(action)
            }
        }

        val restActions = payload.lowLevelEditScript.filter { !payload.isUsed(it) }

        return payload.copy(lowLevelEditScript = restActions)
    }
}