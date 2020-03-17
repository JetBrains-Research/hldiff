package ru.karvozavr.hldiff.app

import ru.karvozavr.hldiff.data.HLDiffFormatter
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.Pipeline
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import ru.karvozavr.hldiff.steps.MoveActionExtractionStep
import ru.karvozavr.hldiff.steps.NonStatementActionsGroupingStep
import ru.karvozavr.hldiff.steps.StatementActionsGroupingStep
import ru.karvozavr.hldiff.visualisation.VisualisationGenerator
import java.io.File
import java.nio.file.Paths

class HLDiffCLI(private val args: HLDiffArgs) {

    fun runHLDiffForFilePair() {
        val src = Paths.get(args.source).toAbsolutePath().toString()
        val dst = Paths.get(args.destination).toAbsolutePath().toString()
        val lowLevelDiff = FilePairPreprocessor().processFilePair(src, dst)
        val highLevelDiff = HighLevelDiff(lowLevelDiff)

        val pipeline = buildPipeline()

        val result: HighLevelDiff = pipeline.apply(highLevelDiff)


        val codeBefore = File(src).readText()
        val codeAfter = File(dst).readText()
        val formatter = HLDiffFormatter(result, lowLevelDiff.treeContext, codeBefore, codeAfter)
        if (args.visualize) {
            println(VisualisationGenerator().generate(formatter.formatJSON(), codeBefore, codeAfter))
        } else {
            println(formatter.formatJSON())
        }
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