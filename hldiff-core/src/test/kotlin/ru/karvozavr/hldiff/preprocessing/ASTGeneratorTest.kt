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
        val ast = generator.treeContext.root

        assertEquals(ast.children[0].type, ASTNode.TYPE_DECLARATION)
    }

    @Test
    fun testPython() {
        val file = Paths.get(javaClass.classLoader.getResource("a.py")!!.toURI()).toString()
        val generator = ASTGenerator(file)
        val ast = generator.treeContext.root

        ast.preOrder().forEach {
            println(generator.treeContext.getTypeLabel(it))
        }
    }
}