package ru.karvozavr.hldiffservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.service.UserDTO
import ru.karvozavr.hldiffservice.service.UserService

@CrossOrigin(origins = ["*"])
@RestController
class UserController(private val userService: UserService) {

  @PreAuthorize("permitAll()")
  @PostMapping("/user/register")
  fun registerUser(@RequestBody user: UserDTO): Mono<ResponseEntity<UserDTO>> {
    return userService.registerUser(user)
      .map { ok().body(it) }
      .defaultIfEmpty(notFound().build())
  }

  @PreAuthorize("permitAll()")
  @PostMapping("/user/authenticate")
  fun authenticateUser(@RequestBody user: UserDTO): Mono<ResponseEntity<UserDTO>> {
    return userService.authenticateUser(user)
      .map { ok().body(it) }
      .defaultIfEmpty(notFound().build())
  }
}
