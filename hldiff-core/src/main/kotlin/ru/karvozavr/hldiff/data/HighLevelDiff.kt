package ru.karvozavr.hldiff.data

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.tree.TreeContext
import ru.karvozavr.hldiff.language.LanguageInfo
import ru.karvozavr.hldiff.preprocessing.LowLevelDiff

/**
 * High-level difference between 2 code files
 */
data class HighLevelDiff(
        /**
         * AST before changes
         */
        val treeBefore: TreeContext,

        /**
         * AST after changes
         */
        val treeAfter: TreeContext,

        /**
         * AST low-level edit-script obtained from GumTree
         */
        val lowLevelEditScript: List<Action>,

        /**
         * High-level edit-script
         */
        val highLevelEditScript: HighLevelEditScript,

        /**
         * Mappings obtained from GumTree
         */
        val mappings: MappingStore,

        /**
         * Language info object
         */
        val languageInfo: LanguageInfo,

        /**
         * Text of the program before changes
         */
        internal val programBeforeText: String,

        /**
         * Text of the program after changes
         */
        internal val programAfterText: String
) {

    constructor(diff: LowLevelDiff, src: String, dst: String)
            : this(diff.treeBefore, diff.treeAfter, diff.editScript, HighLevelEditScript(), diff.mappings, diff.languageInfo, src, dst)

    private val usedActions = HashSet<Action>()

    fun markUsed(action: Action) {
        usedActions.add(action)
    }

    fun isUsed(action: Action): Boolean {
        return action in usedActions
    }
}
