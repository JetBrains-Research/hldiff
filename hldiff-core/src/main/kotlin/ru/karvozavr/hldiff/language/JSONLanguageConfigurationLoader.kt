package ru.karvozavr.hldiff.language

import com.google.gson.Gson
import java.nio.file.Files
import java.nio.file.Paths

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
        val path = Paths.get("src", "main", "resources", "language")
        Files.list(path).forEach {
            if (it.fileName.toString().endsWith(".json")) {
                val json = it.toFile().readText()
                loadConfiguration(json)
            }
        }
    }

    private fun loadConfiguration(objectString: String) {
        val dto = gson.fromJson(objectString, LanguageDTO::class.java)
        configurations.add(LanguageConfiguration(dto.language, dto.statements.map { it.id to it }.toMap(), dto.blockStatements.toSet(), dto.extensions))
    }

    override fun getLanguageConfigurationForFile(fileName: String): LanguageInfo {
        return configurations.first { it.fileIsOfLanguage(fileName) }
    }
}