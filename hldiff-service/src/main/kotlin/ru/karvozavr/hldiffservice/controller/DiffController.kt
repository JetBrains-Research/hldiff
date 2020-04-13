package ru.karvozavr.hldiffservice.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffRepository

@CrossOrigin
@RestController
class DiffController(private val diffRepository: DiffRepository) {

  @PreAuthorize("permitAll()")
  @GetMapping("/diff/{id}")
  fun getDiffById(@PathVariable(required = true) id: String): Mono<Diff> {
    return diffRepository.findById(id)
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/diff")
  fun getAllDiffs(): Flux<Diff> {
    return diffRepository.findAll()
  }

  @PostMapping("/diff")
  fun uploadDiff(@RequestBody diff: Diff): Mono<Diff> {
    return diffRepository.save(diff)
  }
}
