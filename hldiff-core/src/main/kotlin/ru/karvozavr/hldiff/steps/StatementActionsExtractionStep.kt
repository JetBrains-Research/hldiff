package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Delete
import com.github.gumtreediff.actions.model.Insert
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.language.LanguageInfo
import ru.karvozavr.hldiff.pipeline.PipelineStep


class StatementActionsExtractionStep(private val languageInfo: LanguageInfo) : PipelineStep<HighLevelDiff>() {

    override fun processData(payload: HighLevelDiff): HighLevelDiff {

        payload.lowLevelEditScript.forEach {
            val node = it.node
            val isInsertOrDelete = it is Insert || it is Delete
            if (isInsertOrDelete && languageInfo.isDeclarationOrStatement(node)) {
                val action = StatementsActionsGroupingTraversal(it, payload, languageInfo).groupActions()
                payload.highLevelEditScript.add(action)
            }
        }

        return payload
    }
}