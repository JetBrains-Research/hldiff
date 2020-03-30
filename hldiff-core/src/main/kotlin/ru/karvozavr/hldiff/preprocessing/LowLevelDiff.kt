package ru.karvozavr.hldiff.preprocessing

import com.github.gumtreediff.actions.ActionGenerator
import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.matchers.Matchers
import com.github.gumtreediff.tree.TreeContext
import ru.karvozavr.hldiff.language.LanguageInfo

class LowLevelDiff(val treeBefore: TreeContext, val treeAfter: TreeContext, val languageInfo: LanguageInfo, val treeContext: TreeContext) {

    val mappings: MappingStore

    val editScript: List<Action>

    init {
        val before = treeBefore.root
        val after = treeAfter.root

        val matcher = Matchers.getInstance().getMatcher(before, after)
        matcher.match()
        mappings = matcher.mappings

        val generator = ActionGenerator(before, after, mappings)
        generator.generate()
        editScript = generator.actions
    }
}