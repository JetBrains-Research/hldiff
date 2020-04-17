package ru.karvozavr.hldiffservice.data

data class EvaluationDTO(
  var id: String?,
  var diffId: String,
  var author: String,
  var actionsEvaluation: MutableList<ActionEvaluation>,
  var comment: String?
)
