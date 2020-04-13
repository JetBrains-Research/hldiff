package ru.karvozavr.hldiffservice.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffRepository
import ru.karvozavr.hldiffservice.security.SecurityConfig

@WebFluxTest(excludeAutoConfiguration = [SecurityAutoConfiguration::class, SecurityConfig::class])
@RunWith(SpringRunner::class)
internal class DiffControllerTest {

  @MockBean
  private lateinit var diffRepository: DiffRepository

  @Autowired
  private lateinit var webClient: WebTestClient

  @Test
  fun testGetDiff() {
    Mockito.`when`(diffRepository.findById(anyString()))
      .thenReturn(Mono.just(Diff("42", "data", "github", mutableListOf())))

    webClient
      .get()
      .uri("/diff/42")
      .exchange()
      .expectStatus().isOk
      .expectHeader().contentType(MediaType.APPLICATION_JSON)
      .expectBody().jsonPath("@.data", "data")
  }
}
