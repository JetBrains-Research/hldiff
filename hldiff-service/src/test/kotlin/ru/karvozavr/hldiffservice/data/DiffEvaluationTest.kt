package ru.karvozavr.hldiffservice.data

import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Test


internal class DiffEvaluationTest {

  @Test
  fun hasRequiredProperties() {
    val actionEval = ActionEvaluation(42, "good", "well done grouping")
    val evaluation = DiffEvaluation("42", "dmitrii", mutableListOf(actionEval), "comment")

    assertThat(evaluation.author, equalToIgnoringCase("dmitrii"))
    assertThat(evaluation.id, equalTo("42"))
    assertThat(evaluation.actionsEvaluation, hasSize(1))
    assertThat(evaluation.actionsEvaluation[0], equalTo(actionEval))
    assertThat(evaluation.comment, equalToIgnoringCase("comment"))
  }
}
