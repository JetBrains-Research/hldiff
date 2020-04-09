package ru.karvozavr.hldiffservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HldiffServiceApplication

fun main(args: Array<String>) {
	runApplication<HldiffServiceApplication>(*args)
}
