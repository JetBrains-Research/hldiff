package ru.karvozavr.hldiffservice.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface DiffRepository : ReactiveCrudRepository<Diff, String>
