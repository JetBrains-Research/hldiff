package ru.karvozavr.hldiffservice.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.DiffEvaluation
import ru.karvozavr.hldiffservice.data.EvaluationDTO
import ru.karvozavr.hldiffservice.repository.EvaluationRepository

@Service
class EvaluationService(private val evaluationRepository: EvaluationRepository) {

  fun saveEvaluation(evaluation: EvaluationDTO): Mono<EvaluationDTO> {
    val id = evaluation.author + evaluation.diffId
    return evaluationRepository.save(
      DiffEvaluation(
        id = id,
        diffId = evaluation.diffId,
        author = evaluation.author,
        actionsEvaluation = evaluation.actionsEvaluation,
        comment = evaluation.comment)
    )
      .map { evaluation.copy(id = it.id) }
  }

  fun getEvaluation(diffId: String, author: String): Mono<EvaluationDTO> {
    return evaluationRepository.findByDiffIdAndAuthor(diffId, author)
      .map { EvaluationDTO(it.id, it.diffId, it.author, it.actionsEvaluation, it.comment) }
  }
}
