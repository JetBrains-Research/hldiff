package ru.karvozavr.hldiffservice.data

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface EvaluationRepository : ReactiveMongoRepository<DiffEvaluation, String> {

  fun findByDiffIdAndAuthor(diffId: String, author: String): Mono<DiffEvaluation>
}
