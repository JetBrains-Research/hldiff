package ru.karvozavr.hldiffservice.data.auth

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository : ReactiveMongoRepository<User, String>
