package ru.karvozavr.hldiff.data

import com.github.gumtreediff.actions.EditScript
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.tree.ITree
import ru.karvozavr.hldiff.preprocessing.LowLevelDiff

data class HighLevelDiff(val treeBefore: ITree,
                         val treeAfter: ITree,
                         val lowLevelEditScript: EditScript,
                         val highLevelEditScript: HighLevelEditScript,
                         val mappings: MappingStore) {

    constructor(diff: LowLevelDiff)
            : this(diff.treeBefore, diff.treeAfter, diff.editScript, HighLevelEditScript(), diff.mappings)

}