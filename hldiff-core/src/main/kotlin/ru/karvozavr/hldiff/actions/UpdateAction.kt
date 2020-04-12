package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext

class UpdateAction(node: ITree, val type: String, val actions: List<Action>) : HighLevelAction(node) {

    override fun format(context: TreeContext, before: String, after: String): String {
        val sb = StringBuilder()
        sb.append("Update $type with\n")
        return sb.toString()
    }

    override fun getActionType(): String {
        return "update"
    }

    override fun toString(): String {
        return "Update $type"
    }
}
