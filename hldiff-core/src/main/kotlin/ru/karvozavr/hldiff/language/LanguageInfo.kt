package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree

interface LanguageInfo {

    fun isDeclarationOrStatement(node: ITree): Boolean

    fun isComplexStatment(node: ITree): Boolean

    fun isAtomicStatment(node: ITree): Boolean
            = !isComplexStatment(node)

    fun isBaseElement(node: ITree, of: ITree): Boolean

    fun isComposingElement(node: ITree, of: ITree): Boolean
}