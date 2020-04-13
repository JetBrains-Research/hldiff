package ru.karvozavr.hldiffservice.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.auth.User
import ru.karvozavr.hldiffservice.data.auth.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

  fun registerUser(user: UserDTO): Mono<UserDTO> {
    return userRepository.save(User(user.username, user.password))
      .map { UserDTO(it.username, it.password) }
  }
}
