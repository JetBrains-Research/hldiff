package ru.karvozavr.hldiff.preprocessing

class FilePairPreprocessor {

    fun processFilePair(before: String, after: String): LowLevelDiff {
        val astBefore = ASTGenerator(before).getAST()
        val astAfter = ASTGenerator(after).getAST()
        return LowLevelDiff(astBefore, astAfter)
    }
}