package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext

class MoveAction(node: ITree, val type: String, val parent: ITree, val newParent: String, val position: Int) : HighLevelAction(node) {

    override fun format(context: TreeContext, before: String, after: String): String {
        return toString()
    }

    override fun getActionType(): String {
        return "move"
    }

    override fun toString(): String {
        return "Move $type to $newParent at position $position"
    }
}