package ru.karvozavr.hldiff.steps

interface TreeTraversal<TreeT> {

    fun traverse(root: TreeT)

    fun nodeHandler(node: TreeT)
}

