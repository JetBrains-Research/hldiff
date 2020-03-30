package ru.karvozavr.hldiff.app

import com.xenomachina.argparser.ArgParser

class HLDiffArgs(parser: ArgParser) {

    val source by parser.positional("SOURCE", help = "source filename")
    val destination by parser.positional("DEST", help = "destination filename")
    val visualize by parser.flagging("--html", help = "output html visualisation")
}