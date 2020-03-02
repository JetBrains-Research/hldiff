package ru.karvozavr.hldiff.language

interface LanguageConfigurationLoader {

    fun getLanguageConfigurationForFile(fileName: String): LanguageInfo
}