package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.data.HighLevelDiff

abstract class TopDownASTTraversal : TreeTraversal<ITree> {

    override fun traverse(root: ITree) {
        root.preOrder().forEach { traverse(it) }
    }
}