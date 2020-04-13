package ru.karvozavr.hldiffservice.data.auth

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {
  fun findByUsername(username: String): Mono<User>
  fun existsByUsername(username: String): Boolean
}
