package ru.karvozavr.hldiff.data

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.tree.ITree
import com.github.gumtreediff.tree.TreeContext
import ru.karvozavr.hldiff.language.LanguageInfo
import ru.karvozavr.hldiff.preprocessing.LowLevelDiff

data class HighLevelDiff(val treeBefore: TreeContext,
                         val treeAfter: TreeContext,
                         val lowLevelEditScript: List<Action>,
                         val highLevelEditScript: HighLevelEditScript,
                         val mappings: MappingStore,
                         val languageInfo: LanguageInfo,
                         val programBeforeText: String?,
                         val programAfterText: String?) {

    constructor(diff: LowLevelDiff)
            : this(diff.treeBefore, diff.treeAfter, diff.editScript, HighLevelEditScript(), diff.mappings, diff.languageInfo, null, null) // TODO

    private val usedActions = HashSet<Action>()

    fun markUsed(action: Action) {
        usedActions.add(action)
    }

    fun isUsed(action: Action): Boolean {
        return action in usedActions
    }
}