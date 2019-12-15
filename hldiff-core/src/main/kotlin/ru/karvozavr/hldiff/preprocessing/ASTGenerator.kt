package ru.karvozavr.hldiff.preprocessing

import com.github.gumtreediff.client.Run
import com.github.gumtreediff.gen.Generators
import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext

class ASTGenerator(file: String) {

    companion object {
        init {
            Run.initGenerators()
        }
    }

    val treeContext: TreeContext = Generators.getInstance().getTree(file)
}