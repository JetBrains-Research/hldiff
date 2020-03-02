package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree

abstract class LanguageInfo {

    abstract fun isDeclarationOrStatement(node: ITree): Boolean

    abstract fun isComplex(node: ITree): Boolean

    abstract fun isAtomic(node: ITree): Boolean

    abstract fun isBaseElement(node: ITree, of: ITree): Boolean

    abstract fun isComposingElement(node: ITree, of: ITree): Boolean

    abstract fun isBlock(node: ITree): Boolean

    abstract fun getTypeName(node: ITree): String
}