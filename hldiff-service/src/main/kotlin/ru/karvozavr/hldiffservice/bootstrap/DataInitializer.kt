package ru.karvozavr.hldiffservice.bootstrap

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffEvaluation
import ru.karvozavr.hldiffservice.repository.DiffRepository
import ru.karvozavr.hldiffservice.repository.EvaluationRepository

@Component
class DataInitializer(
  private val diffRepository: DiffRepository,
  private val evaluationRepository: EvaluationRepository
) : ApplicationListener<ContextRefreshedEvent> {

  private val logger = LoggerFactory.getLogger(DataInitializer::class.java)

  override fun onApplicationEvent(event: ContextRefreshedEvent) {
    logger.info("Initializing data.")

    evaluationRepository.findAll()
      .flatMap { evaluation ->
        diffRepository.findById(evaluation.diffId)
          .map { it.reviews.add(evaluation.author); it }
          .flatMap { logger.info("saving ${it.source} ${it.reviews}") ; diffRepository.save(it) }
      }
      .subscribe()
  }
}
