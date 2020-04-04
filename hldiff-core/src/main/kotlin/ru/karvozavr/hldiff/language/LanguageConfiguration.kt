package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree

class LanguageConfiguration(val name: String,
                            private val statements: Map<Int, StatementInfo>,
                            private val blockStatements: Set<Int>,
                            private val extensions: Array<String>) : LanguageInfo() {

    fun fileIsOfLanguage(fileName: String) = extensions.any {
        fileName.endsWith(it)
    }

    override fun isDeclarationOrStatement(node: ITree): Boolean {
        val type = node.type
        val kind = statements[type]?.type
        return "atomic" == kind || "nested" == kind
    }

    override fun isComplex(node: ITree): Boolean {
        val type = node.type
        return "complex" == statements[type]?.type
    }

    override fun isAtomic(node: ITree): Boolean {
        val type = node.type
        return "atomic" == statements[type]?.type
    }

    override fun isBlock(node: ITree): Boolean {
        return node.type in blockStatements
    }

    override fun getTypeName(node: ITree): String {
        return statements[node.type]?.name ?: "UNKNOWN STATEMENT: ${node.type}"
    }

    override fun isBaseElement(node: ITree, of: ITree): Boolean {
        return isDeclarationOrStatement(node) && !isComposingElement(node, of)
    }

    override fun isComposingElement(node: ITree, of: ITree): Boolean {
        if (isAtomic(of)) return false

        val isNonBlockChild = node.parent == of && !isBlock(node) && isDeclarationOrStatement(node)
        val isComposingBlock = node.parent.parent == of && isBlock(node)

        return isNonBlockChild || isComposingBlock
    }

}
