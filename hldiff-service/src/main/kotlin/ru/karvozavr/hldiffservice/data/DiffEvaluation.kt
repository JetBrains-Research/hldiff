package ru.karvozavr.hldiffservice.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "diff-evaluation")
data class DiffEvaluation(
  @Id
  var id: String,
  var author: String,
  var actionsEvaluation: MutableList<ActionEvaluation>,
  var comment: String
)
