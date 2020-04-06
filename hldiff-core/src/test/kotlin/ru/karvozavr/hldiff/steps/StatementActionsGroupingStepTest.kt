package ru.karvozavr.hldiff.steps

import org.junit.Test

import org.junit.Assert.*
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import java.io.File
import java.nio.file.Paths

class StatementActionsGroupingStepTest {

    val src = Paths.get(javaClass.classLoader.getResource("a.java")!!.toURI()).toString()
    val dst = Paths.get(javaClass.classLoader.getResource("b.java")!!.toURI()).toString()
    val lowLevelDiff = FilePairPreprocessor().processFilePair(src, dst)
    val hlDiff = HighLevelDiff(lowLevelDiff, File(src).readText(), File(dst).readText())

    @Test
    fun smokeTest() {
        val moveExtractionStep = MoveActionExtractionStep()
        val statementsActionsExtractionStep = StatementActionsGroupingStep()
        val afterMove = moveExtractionStep.apply(hlDiff)

        val result = statementsActionsExtractionStep.apply(afterMove)
        result.highLevelEditScript.forEach { println(it) }

        assertTrue(result.lowLevelEditScript.none { hlDiff.languageInfo.isDeclarationOrStatement(it.node) })
    }
}
