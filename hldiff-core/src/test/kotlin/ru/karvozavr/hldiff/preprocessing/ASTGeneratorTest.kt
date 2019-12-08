package ru.karvozavr.hldiff.preprocessing

import org.eclipse.jdt.core.dom.ASTNode
import org.junit.Test

import org.junit.Assert.*
import java.nio.file.Paths

class ASTGeneratorTest {

    /**
     * Note:
     */
    @Test
    fun smokeTest() {
        val file = Paths.get(javaClass.classLoader.getResource("a.java")!!.toURI()).toString()
        val generator = ASTGenerator(file)
        val ast = generator.getAST()

        assertEquals(ast.children[0].type, ASTNode.TYPE_DECLARATION)
    }
}