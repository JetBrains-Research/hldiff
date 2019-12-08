package ru.karvozavr.hldiff.actions

import com.github.gumtreediff.actions.model.*
import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.language.JavaLanguageInfo

abstract class HighLevelAction(val node: ITree) {

    companion object {

        fun of(lowLevelAction: Action, isPartial: Boolean = false): HighLevelAction {
            val node = lowLevelAction.node

            val nodeTypeString = JavaLanguageInfo.getTypeName(node)
            val actionObjectType = if (isPartial) nodeTypeString + "Part" else nodeTypeString

            return when (lowLevelAction) {
                is Delete -> DeleteAction(node, actionObjectType)
                is Update -> TODO()
                is Move -> InsertAction(node, actionObjectType, lowLevelAction.parent, lowLevelAction.position)
                is Insert -> InsertAction(node, actionObjectType, lowLevelAction.parent, lowLevelAction.position)
                else -> throw RuntimeException("Low-level action of unknown type - ${lowLevelAction.javaClass}.")
            }
        }
    }
}