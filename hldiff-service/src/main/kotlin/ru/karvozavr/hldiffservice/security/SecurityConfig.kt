package ru.karvozavr.hldiffservice.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration


@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

  @Bean
  fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
    return http
      .httpBasic().and()
      .formLogin().disable()
      .csrf().disable()
      .cors().configurationSource {
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        config
      }.and()
      .authorizeExchange()
      .pathMatchers("/**").permitAll()
      .anyExchange().authenticated().and()
      .build()
  }

}
