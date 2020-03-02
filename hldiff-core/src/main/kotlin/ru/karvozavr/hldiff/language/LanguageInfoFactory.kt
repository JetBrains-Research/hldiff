package ru.karvozavr.hldiff.language

import com.github.gumtreediff.tree.TreeContext
import java.lang.RuntimeException

object LanguageInfoFactory {

    fun fromFilename(filename: String, treeContext: TreeContext): LanguageInfo {
        if (filename.endsWith(".java")) {
            return JavaLanguageInfo()
        }

        if (filename.endsWith(".py")) {
            return JavaLanguageInfo()
        }

        throw RuntimeException("No language info found for the file: \"$filename\".")
    }
}