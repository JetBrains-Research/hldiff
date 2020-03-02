package ru.karvozavr.hldiff.preprocessing

import ru.karvozavr.hldiff.language.JSONLanguageConfigurationLoader

class FilePairPreprocessor {

    fun processFilePair(before: String, after: String): LowLevelDiff {
        val astBefore = ASTGenerator(before).treeContext
        val astAfter = ASTGenerator(after).treeContext
        astAfter.importTypeLabels(astBefore)
        return LowLevelDiff(astBefore.root, astAfter.root, JSONLanguageConfigurationLoader.getLanguageConfigurationForFile(before), astAfter)
    }
}