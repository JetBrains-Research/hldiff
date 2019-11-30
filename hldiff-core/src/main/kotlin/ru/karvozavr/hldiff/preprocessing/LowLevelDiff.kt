package ru.karvozavr.hldiff.preprocessing

import com.github.gumtreediff.actions.ChawatheScriptGenerator
import com.github.gumtreediff.actions.EditScript
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.matchers.Matchers
import com.github.gumtreediff.tree.ITree

class LowLevelDiff(val treeBefore: ITree, val treeAfter: ITree) {

    val mappings: MappingStore = Matchers.getInstance().matcher.match(treeBefore, treeAfter)
    val editScript: EditScript = ChawatheScriptGenerator().computeActions(mappings)
}