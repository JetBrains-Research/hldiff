package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree

class UpdateAction(node: ITree, val type: String, val actions: List<Action>) : HighLevelAction(node) {

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Update $type with\n")
        actions.forEach {
            sb.append("\t\t$it\n")
        }
        return sb.toString()
    }
}