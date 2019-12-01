package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.data.HighLevelDiff

abstract class TopDownASTTraversal : TreeTraversal<ITree, HighLevelDiff> {

    override fun traverse(root: ITree, context: HighLevelDiff) {
        root.preOrder().forEach { traverse(it, context) }
    }
}