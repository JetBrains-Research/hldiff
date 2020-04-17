package ru.karvozavr.hldiffservice.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.EvaluationDTO
import ru.karvozavr.hldiffservice.service.EvaluationService

@CrossOrigin(origins = ["*"])
@RestController
class EvaluationController(private val evaluationService: EvaluationService) {

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/evaluation")
  fun getEvaluation(@RequestParam(required = true) diffId: String,
                    @RequestParam(required = true) author: String): Mono<EvaluationDTO> {
    return evaluationService.getEvaluation(diffId, author)
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping("/evaluation")
  fun saveEvaluation(@RequestBody evaluation: EvaluationDTO): Mono<EvaluationDTO> {
    return evaluationService.saveEvaluation(evaluation)
  }
}
