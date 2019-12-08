package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree
import org.eclipse.jdt.core.dom.ASTNode

object JavaLanguageInfo : LanguageInfo {

    private val atomicStatements: Set<Int> = setOf(
            ASTNode.VARIABLE_DECLARATION_STATEMENT,
            ASTNode.EXPRESSION_STATEMENT,
            ASTNode.ASSERT_STATEMENT,
            ASTNode.BREAK_STATEMENT,
            ASTNode.CONTINUE_STATEMENT,
            ASTNode.THROW_STATEMENT,
            ASTNode.CONSTRUCTOR_INVOCATION,
            ASTNode.SUPER_CONSTRUCTOR_INVOCATION,
            ASTNode.LABELED_STATEMENT
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

    fun getTypeName(node: ITree): String {
        return ASTNode.nodeClassForType(node.type).simpleName
    }

    override fun isDeclarationOrStatement(node: ITree): Boolean {
        val type = node.type
        return type in atomicDeclarations ||
                type in complexDeclarations ||
                type in atomicStatements ||
                type in complexStatements
    }

    override fun isComplex(node: ITree): Boolean {
        val type = node.type
        return type in complexDeclarations || type in complexStatements
    }

    override fun isAtomic(node: ITree): Boolean {
        val type = node.type
        return type in atomicDeclarations || type in atomicStatements
    }

    override fun isBaseElement(node: ITree, of: ITree): Boolean {
        return isDeclarationOrStatement(node) && !isComposingElement(node, of)
    }

    override fun isComposingElement(node: ITree, of: ITree): Boolean {
        if (isAtomic(of)) return false
        val type = node.type
        val blockType = ASTNode.BLOCK

        val isNonBlockChild = node.parent == of && type != blockType && isDeclarationOrStatement(node)
        val isComposingBlock = node.parent.parent == of && type == blockType

        return isNonBlockChild || isComposingBlock
    }

}