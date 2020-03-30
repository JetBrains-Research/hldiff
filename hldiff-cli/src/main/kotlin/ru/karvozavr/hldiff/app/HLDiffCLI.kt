package ru.karvozavr.hldiff.app

import ru.karvozavr.hldiff.api.HLDiffAPI
import ru.karvozavr.hldiff.data.HLDiffFormatter
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.visualisation.VisualisationGenerator
import java.io.File
import java.nio.file.Paths

class HLDiffCLI(private val args: HLDiffArgs) {

    val api: HLDiffAPI = HLDiffAPI()

    fun runHLDiffForFilePair() {
        val src = Paths.get(args.source).toAbsolutePath().toString()
        val dst = Paths.get(args.destination).toAbsolutePath().toString()
        val result: HighLevelDiff = api.getHighLevelDiff(src, dst)

        val codeBefore = File(src).readText()
        val codeAfter = File(dst).readText()
        val formatter = HLDiffFormatter(result, codeBefore, codeAfter)

        if (args.visualize) {
            println(VisualisationGenerator().generate(formatter.formatJSON(), codeBefore, codeAfter))
        } else {
            println(formatter.formatJSON())
        }
    }
}