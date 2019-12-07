package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree

object JavaLanguageInfo : LanguageInfo {

    val statements: Set<String> = setOf("VariableDeclarationStatement")

    override fun isDeclarationOrStatement(node: ITree): Boolean {
        node.type
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isComplexStatment(node: ITree): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isBaseElement(node: ITree, of: ITree): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isComposingElement(node: ITree, of: ITree): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}