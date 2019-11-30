package ru.karvozavr.hldiff.preprocessing

import org.junit.Test

import org.junit.Assert.*
import java.nio.file.Paths

class FilePairPreprocessorTest {

    @Test
    fun smokeTest() {
        val src = Paths.get(javaClass.classLoader.getResource("a.java")!!.toURI()).toString()
        val dst = Paths.get(javaClass.classLoader.getResource("b.java")!!.toURI()).toString()

        val lowLevelDiff = FilePairPreprocessor().processFilePair(src, dst)
        assertTrue(lowLevelDiff.editScript.size() > 0)
    }
}