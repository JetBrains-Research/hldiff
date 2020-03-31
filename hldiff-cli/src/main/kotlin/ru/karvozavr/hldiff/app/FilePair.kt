package ru.karvozavr.hldiff.app

import java.nio.file.Paths

data class FilePair(val fileBefore: String, val fileAfter: String) {

    val diffName = "${Paths.get(fileBefore).fileName}__${Paths.get(fileAfter).fileName}"
}