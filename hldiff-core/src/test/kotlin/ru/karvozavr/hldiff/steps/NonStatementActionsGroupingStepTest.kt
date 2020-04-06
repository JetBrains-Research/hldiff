package ru.karvozavr.hldiff.steps

import org.junit.Test
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import java.io.File
import java.nio.file.Paths

class NonStatementActionsGroupingStepTest {

    val src = Paths.get(javaClass.classLoader.getResource("a.java")!!.toURI()).toString()
    val dst = Paths.get(javaClass.classLoader.getResource("b.java")!!.toURI()).toString()
    val lowLevelDiff = FilePairPreprocessor().processFilePair(src, dst)
    val hlDiff = HighLevelDiff(lowLevelDiff, File(src).readText(), File(dst).readText())

    @Test
    fun smokeTest() {
        val moveExtractionStep = MoveActionExtractionStep()
        val statementsActionsGroupingStep = StatementActionsGroupingStep()
        val nonStatementActionsGroupingStep = NonStatementActionsGroupingStep()

        val afterMove = moveExtractionStep.apply(hlDiff)
        val afterSt = statementsActionsGroupingStep.apply(afterMove)
        val afterNonSt = nonStatementActionsGroupingStep.apply(afterSt)

        afterNonSt.highLevelEditScript.forEach { println(it) }

       // assertTrue(afterSt.lowLevelEditScript.none { JavaLanguageInfo.isDeclarationOrStatement(it.node) })
    }
}
