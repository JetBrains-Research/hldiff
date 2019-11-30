package ru.karvozavr.hldiff.preprocessing

import org.junit.Test

import org.junit.Assert.*
import java.nio.file.Paths

class ASTGeneratorTest {

    @Test
    fun smokeTest() {
        val file = Paths.get(javaClass.classLoader.getResource("a.java")!!.toURI()).toString()
        val generator = ASTGenerator(file)

        assertEquals(generator.getAST().children[0].type.toString(), "TypeDeclaration")
    }
}