package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree

interface LanguageInfo {

    fun isDeclarationOrStatement(node: ITree): Boolean

    fun isComplex(node: ITree): Boolean

    fun isAtomic(node: ITree): Boolean

    fun isBaseElement(node: ITree, of: ITree): Boolean

    fun isComposingElement(node: ITree, of: ITree): Boolean
    fun getTypeName(node: ITree): String
}