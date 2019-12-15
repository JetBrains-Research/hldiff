package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext

abstract class LanguageInfo(private val context: TreeContext) {

    abstract fun isDeclarationOrStatement(node: ITree): Boolean

    abstract fun isComplex(node: ITree): Boolean

    abstract fun isAtomic(node: ITree): Boolean

    abstract fun isBaseElement(node: ITree, of: ITree): Boolean

    abstract fun isComposingElement(node: ITree, of: ITree): Boolean

    fun getTypeName(node: ITree): String {
        return context.getTypeLabel(node)
    }
}