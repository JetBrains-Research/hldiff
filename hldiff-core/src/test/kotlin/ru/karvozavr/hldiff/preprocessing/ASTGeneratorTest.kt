package ru.karvozavr.hldiff.preprocessing

import org.eclipse.jdt.core.dom.ASTNode
import org.junit.Assert.assertEquals
import org.junit.Test
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

        assertEquals(ASTNode.TYPE_DECLARATION, ast.children[0].type)
    }

    @Test
    fun testPython() {
        val file = Paths.get(javaClass.classLoader.getResource("b.py")!!.toURI()).toString()
        val generator = ASTGenerator(file)
        val ast = generator.treeContext.root

        ast.preOrder().forEach {
            repeat(it.depth) { print("\t|") }
            println("${generator.treeContext.getTypeLabel(it)} : ${it.label}")
        }

        assertEquals("FunctionDef", generator.treeContext.getTypeLabel(ast.getChild(0)))
    }
}