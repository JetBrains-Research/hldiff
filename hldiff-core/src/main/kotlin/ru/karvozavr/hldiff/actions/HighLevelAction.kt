package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.actions.model.*
import com.github.gumtreediff.tree.ITree

abstract class HighLevelAction(val node: ITree) {

    companion object {

        fun of(lowLevelAction: Action, isPartial: Boolean = false): HighLevelAction {
            val node = lowLevelAction.node

            val nodeType = if (isPartial) node.type.toString() else node.type.toString() + "Part"

            return when (lowLevelAction) {
                is Delete -> TODO()
                is Update -> TODO()
                is Move -> {
                    InsertAction(node, nodeType, lowLevelAction.parent, lowLevelAction.position)
                }
                is Insert -> {
                    InsertAction(node, nodeType, lowLevelAction.parent, lowLevelAction.position)
                }
                else -> throw RuntimeException("Low-level action of unknown type - ${lowLevelAction.javaClass}.")
            }
        }
    }
}