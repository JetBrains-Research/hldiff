package ru.karvozavr.hldiff.language

import com.google.gson.Gson
import com.google.gson.JsonObject

class LanguageDTO(
        val language: String,
        val statements: Array<StatementInfo>,
        val extensions: Array<String>,
        val blockStatements: Array<Int>
)

object JSONLanguageConfigurationLoader : LanguageConfigurationLoader {

    private val gson = Gson()
    private val configurations = mutableListOf<LanguageConfiguration>()

    init {
        val languages = loadLanguagesList()
        languages.forEach {
            val json = this.javaClass.classLoader.getResourceAsStream("language/$it").reader().readText()
            loadConfiguration(json)
        }
    }

    private fun loadLanguagesList(): List<String> {
        val json = this.javaClass.classLoader.getResourceAsStream("language/languages").reader().readText()
        val languages = gson.fromJson(json, JsonObject::class.java)
        return languages.get("configurations").asJsonArray.map { it.asString }.toList()
    }

    private fun loadConfiguration(objectString: String) {
        val dto = gson.fromJson(objectString, LanguageDTO::class.java)
        configurations.add(LanguageConfiguration(dto.language, dto.statements.map { it.id to it }.toMap(), dto.blockStatements.toSet(), dto.extensions))
    }

    override fun getLanguageConfigurationForFile(fileName: String): LanguageInfo {
        return configurations.firstOrNull { it.fileIsOfLanguage(fileName) }
                ?: throw LanguageConfigurationLoadException("Configuration for file ${fileName} not found.")

    }

    override fun getLanguageConfigurationByLanguageName(languageName: String): LanguageInfo {
        return configurations.firstOrNull { it.name == languageName }
                ?: throw LanguageConfigurationLoadException("Configuration for language ${languageName} not found.")
    }
}