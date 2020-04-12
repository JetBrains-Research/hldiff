package ru.karvozavr.hldiffservice.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffRepository

@CrossOrigin
@RestController
class DiffController(private val diffRepository: DiffRepository) {

  @GetMapping("/diff/{id}")
  fun getDiffById(@PathVariable(required = true) id: String): Mono<Diff> {
    return diffRepository.findById(id)
  }

  @GetMapping("/diff")
  fun getAllDiffs(@PathVariable(required = false) id: String?): Flux<Diff> {
    return diffRepository.findAll()
  }

  @PostMapping("/diff")
  fun uploadDiff(@RequestBody diff: Diff): Mono<Diff> {
    return diffRepository.save(diff)
  }
}
