package ru.karvozavr.hldiff.language

import org.eclipse.jdt.core.dom.ASTNode
import org.junit.Assert
import org.junit.Test
import ru.karvozavr.hldiff.pipeline.Pipeline
import ru.karvozavr.hldiff.pipeline.PipelineStep
import ru.karvozavr.hldiff.preprocessing.FilePairPreprocessor
import java.nio.file.Paths

private val atomicStatements: Set<Int> = setOf(
        ASTNode.VARIABLE_DECLARATION_STATEMENT,
        ASTNode.EXPRESSION_STATEMENT,
        ASTNode.ASSERT_STATEMENT,
        ASTNode.BREAK_STATEMENT,
        ASTNode.CONTINUE_STATEMENT,
        ASTNode.THROW_STATEMENT,
        ASTNode.CONSTRUCTOR_INVOCATION,
        ASTNode.SUPER_CONSTRUCTOR_INVOCATION,
        ASTNode.LABELED_STATEMENT,
        ASTNode.IMPORT_DECLARATION,
        ASTNode.MARKER_ANNOTATION
)

private val complexStatements: Set<Int> = setOf(
        ASTNode.IF_STATEMENT,
        ASTNode.BLOCK,
        ASTNode.FOR_STATEMENT,
        ASTNode.ENHANCED_FOR_STATEMENT,
        ASTNode.DO_STATEMENT,
        ASTNode.WHILE_STATEMENT,
        ASTNode.TRY_STATEMENT,
        ASTNode.CATCH_CLAUSE,
        ASTNode.SYNCHRONIZED_STATEMENT,
        ASTNode.SWITCH_STATEMENT,
        ASTNode.SWITCH_CASE
)

private val complexDeclarations: Set<Int> = setOf(
        ASTNode.METHOD_DECLARATION,
        ASTNode.FIELD_DECLARATION,
        ASTNode.TYPE_DECLARATION,
        ASTNode.INITIALIZER,
        ASTNode.COMPILATION_UNIT,
        ASTNode.ANONYMOUS_CLASS_DECLARATION,
        ASTNode.ENUM_DECLARATION
)

private val atomicDeclarations: Set<Int> = setOf(
        ASTNode.ENUM_CONSTANT_DECLARATION,
        ASTNode.VARIABLE_DECLARATION_FRAGMENT,
        ASTNode.SINGLE_VARIABLE_DECLARATION
)

class LanguageConfigTest {

    @Test
    fun testSimplePipeline() {
        val atomic = atomicDeclarations + atomicStatements
        val m = mutableMapOf<Int, String>()
        atomic.forEach { m[it] = "atomic" }

        val nested = complexDeclarations + complexStatements
        nested.forEach { m[it] = "nested" }

        m.entries.sortedBy { it.key }.forEach { print("{\"id\": ${it.key}, \"type\": \"${it.value}\", \"name\": \"${ASTNode.nodeClassForType(it.key).simpleName}\"},\n") }
    }
}