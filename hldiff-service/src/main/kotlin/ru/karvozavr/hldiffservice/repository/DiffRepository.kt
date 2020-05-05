package ru.karvozavr.hldiffservice.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ru.karvozavr.hldiffservice.data.Diff

interface DiffRepository : ReactiveCrudRepository<Diff, String>
