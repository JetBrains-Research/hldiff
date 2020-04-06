package ru.karvozavr.hldiff.steps

import org.junit.Assert.assertTrue
import org.junit.Test
import ru.karvozavr.hldiff.actions.MoveAction
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import java.io.File
import java.nio.file.Paths

class MoveActionExtractionStepTest {

    @Test
    fun testMoveActionExtraction() {
        val src = Paths.get(javaClass.classLoader.getResource("a.java")!!.toURI()).toString()
        val dst = Paths.get(javaClass.classLoader.getResource("b.java")!!.toURI()).toString()

        val lowLevelDiff = FilePairPreprocessor().processFilePair(src, dst)

        val step = MoveActionExtractionStep()
        val hlDiff = HighLevelDiff(lowLevelDiff, File(src).readText(), File(dst).readText())
        val resultDiff = step.apply(hlDiff)

        assertTrue(resultDiff.highLevelEditScript[0] is MoveAction)
    }
}
