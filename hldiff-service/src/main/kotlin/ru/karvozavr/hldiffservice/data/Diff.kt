package ru.karvozavr.hldiffservice.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "high-level-diffs")
data class Diff(
  @Id
  var id: String?,
  var data: String,
  var source: String?
)
