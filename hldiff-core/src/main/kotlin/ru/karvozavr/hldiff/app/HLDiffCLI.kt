package ru.karvozavr.hldiff.app

import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.Pipeline
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import ru.karvozavr.hldiff.steps.MoveActionExtractionStep
import ru.karvozavr.hldiff.steps.NonStatementActionsGroupingStep
import ru.karvozavr.hldiff.steps.StatementActionsGroupingStep
import java.nio.file.Paths

class HLDiffCLI(private val args: HLDiffArgs) {

    fun runHLDiffForFilePair() {
        val src = Paths.get(args.source).toString()
        val dst = Paths.get(args.destination).toString()
        val lowLevelDiff = FilePairPreprocessor().processFilePair(src, dst)
        val highLevelDiff = HighLevelDiff(lowLevelDiff)

        val pipeline = buildPipeline()

        val result: HighLevelDiff = pipeline.apply(highLevelDiff)

        printHighLevelEditScript(result)
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

    private fun printHighLevelEditScript(result: HighLevelDiff) {
        result.highLevelEditScript.forEach {
            println(it)
        }
    }
}