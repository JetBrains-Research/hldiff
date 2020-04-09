package ru.karvozavr.hldiffservice.data

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DiffTest {

  @Test
  fun hasId() {
    val diff = Diff("42", "data", null)
    assertEquals("42", diff.id, "Should have valid id")
  }

  @Test
  fun hasData() {
    val diff = Diff("42", "data", null)
    assertEquals("data", diff.data, "Should have valid data")
  }
}
