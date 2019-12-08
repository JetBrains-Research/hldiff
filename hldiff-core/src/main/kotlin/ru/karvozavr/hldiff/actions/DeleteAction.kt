package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.tree.ITree

class DeleteAction(node: ITree, val type: String) : HighLevelAction(node) {

    override fun toString(): String {
        return "Remove $type"
    }
}