package ru.karvozavr.hldiff.preprocessing

import com.github.gumtreediff.client.Run
import com.github.gumtreediff.gen.Generators
import com.github.gumtreediff.gen.Register
import com.github.gumtreediff.gen.python.PythonTreeGenerator
import com.github.gumtreediff.tree.ITree
import kotlin.jvm.javaClass
import com.github.gumtreediff.tree.TreeContext
import java.util.*

val CUSTOM_TREE_GENERATORS = listOf(
        PythonTreeGenerator::class.java
)

class ASTGenerator(file: String) {

    companion object {
        init {
            Run.initGenerators()
            CUSTOM_TREE_GENERATORS.forEach {
                Generators.getInstance().install(it, it.getAnnotation(Register::class.java))
            }
        }
    }

    val treeContext: TreeContext = Generators.getInstance().getTree(file)
}