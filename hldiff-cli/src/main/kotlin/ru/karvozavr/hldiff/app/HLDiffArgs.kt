package ru.karvozavr.hldiff.app

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class HLDiffArgs(parser: ArgParser) {

    val source by parser.positional("SOURCE", help = "source code (before changes) filename").default("")

    val destination by parser.positional("DEST", help = "destination code (after changes) filename").default("")

    val visualizeHtml by parser.flagging("--html", help = "output html visualisation")

    val batch by parser.flagging(
            "--batch",
            help = "Process multiple file pairs. File name pairs should be passed to stdin, one pair per line like this: \"fileBefore.java fileAfter.java>\".")

    val outputDirectory: Path by parser.storing(
            "-o", "--outdir",
            help = "Output directory for batch processing.")
    {
        val dir = Paths.get(this)
        if (!Files.exists(dir)) {
            Files.createDirectories(dir)
        } else {
            dir
        }
    }.default(Files.createDirectories(Paths.get("hldiff-output")))
}