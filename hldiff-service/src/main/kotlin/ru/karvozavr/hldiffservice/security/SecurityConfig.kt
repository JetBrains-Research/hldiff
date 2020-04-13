package ru.karvozavr.hldiffservice.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

  @Bean
  fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
    http
      .csrf().disable()

    http
      .authorizeExchange()
      .pathMatchers("/diff/**").permitAll()
      .anyExchange().authenticated()

    http
      .httpBasic()

    http
      .formLogin().disable()

    return http.build();
  }

}
