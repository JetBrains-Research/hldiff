package ru.karvozavr.hldiffservice.bootstrap

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import ru.karvozavr.hldiffservice.data.Diff
import ru.karvozavr.hldiffservice.data.DiffRepository

@Component
class DataInitializer(
  private val diffRepository: DiffRepository
) : ApplicationListener<ContextRefreshedEvent> {

  private val logger = LoggerFactory.getLogger(DataInitializer::class.java)

  override fun onApplicationEvent(event: ContextRefreshedEvent) {
    logger.info("Initializing data.")

    val data = Diff("test_data_id_0001", "{\"data\": \"XYZ\"}", null, mutableListOf())
    diffRepository.save(data).subscribe()
  }
}
