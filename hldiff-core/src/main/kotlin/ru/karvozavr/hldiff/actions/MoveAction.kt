package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.tree.ITree

data class MoveAction(val node: ITree, val label: String, val newParent: ITree, val position: Int) : HighLevelAction {
    override fun toString(): String {
        return "Move $label to $newParent at position $position"
    }
}