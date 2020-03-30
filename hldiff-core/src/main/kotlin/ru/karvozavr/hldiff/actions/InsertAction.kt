package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext

class InsertAction(node: ITree, val type: String, val newParent: String, val position: Int) : HighLevelAction(node) {

    override fun format(context: TreeContext, before: String, after: String): String {
        return toString()
    }

    override fun getActionType(): String {
        return "add"
    }

    override fun toString(): String {
        return "Insert $type to $newParent at position $position"
    }
}