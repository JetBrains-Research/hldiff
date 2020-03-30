package ru.karvozavr.hldiff.language

class LanguageConfigurationLoadException(message: String) : Exception(message)

interface LanguageConfigurationLoader {

    fun getLanguageConfigurationForFile(fileName: String): LanguageInfo

    fun getLanguageConfigurationByLanguageName(languageName: String): LanguageInfo
}