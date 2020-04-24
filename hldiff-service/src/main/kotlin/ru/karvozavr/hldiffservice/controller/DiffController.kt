package ru.karvozavr.hldiffservice.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffRepository
import java.security.Principal

@CrossOrigin(origins = ["*"])
@RestController
class DiffController(private val diffRepository: DiffRepository) {

  @PreAuthorize("permitAll()")
  @GetMapping("/diff/{id}")
  fun getDiffById(@PathVariable(required = true) id: String): Mono<Diff> {
    return diffRepository.findById(id)
  }

  @CrossOrigin(origins = ["*"])
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/diff")
  fun getAllDiffs(principal: Mono<Principal>): Flux<Diff> {
    return diffRepository.findAll()
  }

  @PostMapping("/diff")
  fun uploadDiff(@RequestBody diff: Diff, principal: Mono<Principal>): Mono<Diff> {
    return diffRepository.save(diff)
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/diff/{id}")
  fun deleteDiffById(@PathVariable(required = true) id: String): Mono<Void> {
    return diffRepository.deleteById(id)
  }
}
