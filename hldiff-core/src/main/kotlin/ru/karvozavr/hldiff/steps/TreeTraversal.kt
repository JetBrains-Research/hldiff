package ru.karvozavr.hldiff.steps

interface TreeTraversal<TreeT, ContextT> {

    fun traverse(root: TreeT, context: ContextT)

    fun nodeHandler(node: TreeT, context: ContextT)
}

