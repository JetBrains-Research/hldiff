package ru.karvozavr.hldiff.data

import com.github.gumtreediff.actions.EditScript
import com.github.gumtreediff.tree.Tree

class Diff(val treeBefore: Tree,
           val treeAfter: Tree,
           val lowLevelEditScript: EditScript,
           val highLevelEditScript: HighLevelEditScript)