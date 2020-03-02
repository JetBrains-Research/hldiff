package ru.karvozavr.hldiff.preprocessing

import com.github.gumtreediff.actions.ActionGenerator
import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.matchers.Matchers
import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext
import ru.karvozavr.hldiff.language.LanguageInfo

class LowLevelDiff(val treeBefore: ITree, val treeAfter: ITree, val languageInfo: LanguageInfo, val treeContext: TreeContext) {

    val mappings: MappingStore

    val editScript: List<Action>

    init {
        val matcher = Matchers.getInstance().getMatcher(treeBefore, treeAfter)
        matcher.match()
        mappings = matcher.mappings

        val generator = ActionGenerator(treeBefore, treeAfter, mappings)
        generator.generate()
        editScript = generator.actions
    }
}