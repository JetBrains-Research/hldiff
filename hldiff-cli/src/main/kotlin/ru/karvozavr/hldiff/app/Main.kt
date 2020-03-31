package ru.karvozavr.hldiff.app

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.SystemExitException

fun main(args: Array<String>) {
    val argParser = ArgParser(args)
    val arguments = HLDiffArgs(argParser)

    try {
        val cli = HLDiffCLI(arguments)
        cli.runHLDiff()
    } catch (e: SystemExitException) {
        e.printAndExit()
    }
}