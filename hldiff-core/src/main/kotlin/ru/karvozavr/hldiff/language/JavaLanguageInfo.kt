package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree

object JavaLanguageInfo : LanguageInfo {

    private val atomicStatements: Set<String> = setOf(
            "VariableDeclarationStatement",
            "ExpressionStatement",
            "AssertStatement",
            "BreakStatement",
            "ContinueStatement",
            "ThrowStatement",
            "ConstructorInvocation",
            "SuperConstructorInvocation",
            "LabeledStatement"
    )

    private val complexStatements: Set<String> = setOf(
            "IfStatement",
            "BlockStatement",
            "ForStatement",
            "EnhancedForStatement",
            "DoStatement",
            "WhileStatement",
            "TryStatement",
            "CatchClause",
            "SynchronizedStatement",
            "SwitchStatement",
            "SwitchCase"
    )

    private val complexDeclarations: Set<String> = setOf(
            "MethodDeclaration",
            "FieldDeclaration",
            "TypeDeclaration",
            "InitializerBlock",
            "EnumDeclaration",
            "CompilationUnit",
            "AbstractTypeDeclaration",
            "AnonymousClassDeclaration",
            "SingleVariableDeclaration"
    )

    private val atomicDeclarations: Set<String> = setOf(
            "EnumDeclaration",
            "EnumConstantDeclaration",
            "VariableDeclarationFragment"
    )

    override fun isDeclarationOrStatement(node: ITree): Boolean {
        val type = node.type.toString()
        return type in atomicDeclarations ||
                type in complexDeclarations ||
                type in atomicStatements ||
                type in complexStatements
    }

    override fun isComplex(node: ITree): Boolean {
        val type = node.type.toString()
        return type in complexDeclarations || type in complexStatements
    }

    override fun isAtomic(node: ITree): Boolean {
        val type = node.type.toString()
        return type in atomicDeclarations || type in atomicStatements
    }

    override fun isBaseElement(node: ITree, of: ITree): Boolean {
        return isDeclarationOrStatement(node) && !isComposingElement(node, of)
    }

    override fun isComposingElement(node: ITree, of: ITree): Boolean {
        if (isAtomic(of)) return false
        val type = node.type.toString()
        val blockType = "BlockStatement"

        val isNonBlockChild = node.parent == of && type != blockType && isDeclarationOrStatement(node)
        val isComposingBlock = node.parent.parent == of && type == blockType

        return isNonBlockChild || isComposingBlock
    }

}