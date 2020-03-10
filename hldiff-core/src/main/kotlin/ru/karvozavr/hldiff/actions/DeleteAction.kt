package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext

class DeleteAction(node: ITree, val type: String) : HighLevelAction(node) {

    override fun format(context: TreeContext, before: String, after: String): String {
        return toString()
    }

    override fun getActionType(): String {
        return "delete"
    }

    override fun toString(): String {
        return "Remove $type"
    }
}