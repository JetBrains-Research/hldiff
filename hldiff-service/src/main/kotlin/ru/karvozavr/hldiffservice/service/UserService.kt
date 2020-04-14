package ru.karvozavr.hldiffservice.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.auth.User
import ru.karvozavr.hldiffservice.data.auth.UserRepository

@Service
class UserService(
  private val userRepository: UserRepository,
  private val encoder: BCryptPasswordEncoder
) {

  fun registerUser(user: UserDTO): Mono<UserDTO> {
    return if (user.username.matches(Regex("[A-Za-z0-9_]+"))) {
      userRepository.insert(User(user.username, encoder.encode(user.password)))
        .map { UserDTO(it.username, it.password) }
        .onErrorResume { Mono.empty() }
    } else {
      Mono.empty();
    }
  }

  fun authenticateUser(user: UserDTO): Mono<UserDTO> {
    return userRepository.findById(user.username)
      .filter { encoder.matches(user.password, it.password) }
      .map { user }
  }
}
