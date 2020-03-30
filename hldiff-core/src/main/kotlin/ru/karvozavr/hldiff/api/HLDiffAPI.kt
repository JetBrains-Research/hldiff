package ru.karvozavr.hldiff.api

import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.Pipeline
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import ru.karvozavr.hldiff.steps.MoveActionExtractionStep
import ru.karvozavr.hldiff.steps.NonStatementActionsGroupingStep
import ru.karvozavr.hldiff.steps.StatementActionsGroupingStep

/**
 * HLDiff API
 */
class HLDiffAPI {

    /**
     * Generates a high-level difference between two files
     *
     * Note: to determine the programming language file extension is used
     *
     * @param sourceFile program file before changes
     * @param destinationFile program file after changes
     */
    fun getHighLevelDiff(sourceFile: String, destinationFile: String): HighLevelDiff {
        val lowLevelDiff = FilePairPreprocessor().processFilePair(sourceFile, destinationFile)
        val highLevelDiff = HighLevelDiff(lowLevelDiff)
        val pipeline = buildPipeline()
        return pipeline.apply(highLevelDiff)
    }

    private fun buildPipeline(): Pipeline<HighLevelDiff> {
        val moveExtractionStep = MoveActionExtractionStep()
        val statementsActionsGroupingStep = StatementActionsGroupingStep()
        val nonStatementActionsGroupingStep = NonStatementActionsGroupingStep()

        return Pipeline<HighLevelDiff>()
                .pipe(moveExtractionStep)
                .pipe(statementsActionsGroupingStep)
                .pipe(nonStatementActionsGroupingStep)
    }
}