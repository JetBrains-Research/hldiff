package ru.karvozavr.hldiffservice.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.DiffEvaluation

interface EvaluationRepository : ReactiveMongoRepository<DiffEvaluation, String> {

  fun findByDiffIdAndAuthor(diffId: String, author: String): Mono<DiffEvaluation>
}
