package ru.karvozavr.hldiffservice.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffRepository

@CrossOrigin
@RestController
class DiffController(private val diffRepository: DiffRepository) {

  @GetMapping("/diff/{id}")
  fun getDiffById(@PathVariable id: String): Mono<Diff> {
    return diffRepository.findById(id)
  }
}
