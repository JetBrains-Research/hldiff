package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.tree.ITree

class InsertAction(node: ITree, val type: String, val newParent: ITree, val position: Int) : HighLevelAction(node) {

    override fun toString(): String {
        return "Add $type to $newParent at position $position"
    }
}