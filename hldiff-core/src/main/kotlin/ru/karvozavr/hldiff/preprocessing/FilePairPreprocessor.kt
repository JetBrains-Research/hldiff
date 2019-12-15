package ru.karvozavr.hldiff.preprocessing

import ru.karvozavr.hldiff.language.LanguageInfoFactory

class FilePairPreprocessor {

    fun processFilePair(before: String, after: String): LowLevelDiff {
        val astBefore = ASTGenerator(before).treeContext
        val astAfter = ASTGenerator(after).treeContext
        astAfter.importTypeLabels(astBefore)
        return LowLevelDiff(astBefore.root, astAfter.root, LanguageInfoFactory.fromFilename(before, astAfter))
    }
}