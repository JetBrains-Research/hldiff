package ru.karvozavr.hldiff.app

import ru.karvozavr.hldiff.api.HLDiffAPI
import ru.karvozavr.hldiff.data.HLDiffFormatter
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.visualisation.VisualisationGenerator
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class HLDiffCLI(private val args: HLDiffArgs) {

    private val api: HLDiffAPI = HLDiffAPI()

    fun runHLDiff() {
        if (args.batch) {
            processBatch()
        } else {
            runHLDiffForFilePair()
        }
    }

    private fun processBatch() {
        val outputDir = Files.createDirectories(args.outputDirectory)
        val filePairs = mutableListOf<FilePair>()
        var line: String? = readLine()
        var lineNumber = -1
        while (line != null) {
            ++lineNumber

            val split = line.trim().split(Regex("\\s+"))
            if (split.size != 3) {
                System.err.println("Incorrect input line $lineNumber:\n$line")
                line = readLine()
                continue
            }

            filePairs.add(FilePair(split[0], split[1], split[2]))
            line = readLine()
        }

        runHLDiffForFilePairs(filePairs, outputDir)
    }

    private fun runHLDiffForFilePairs(pairs: List<FilePair>, outputDir: Path) {
        pairs.parallelStream().forEach { pair ->
            try {
                val diff = api.getHighLevelDiff(pair.fileBefore, pair.fileAfter)
                val output = formatDiff(pair.fileBefore, pair.fileAfter, diff)
                val outFilePath = outputDir.resolve(pair.diffName + (if (args.visualizeHtml) ".html" else ".json"))
                outFilePath.toFile().writeText(output)
            } catch (e: Exception) {
                System.err.println("Failed to generate diff for file pair: ${pair.fileBefore} ${pair.fileAfter}")
                e.printStackTrace(System.err)
            }
        }
    }

    private fun runHLDiffForFilePair() {
        val src = args.source
        val dst = args.destination
        val result: HighLevelDiff = api.getHighLevelDiff(src, dst)

        val output = formatDiff(src, dst, result)

        println(output)
    }

    private fun formatDiff(src: String, dst: String, result: HighLevelDiff): String {
        val codeBefore = File(src).readText()
        val codeAfter = File(dst).readText()
        val formatter = HLDiffFormatter(result, codeBefore, codeAfter)

        return if (args.visualizeHtml) {
            VisualisationGenerator().generate(formatter.formatJSON(), codeBefore, codeAfter)
        } else {
            formatter.formatJSON()
        }
    }
}
