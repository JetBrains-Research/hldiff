package ru.karvozavr.hldiffservice.security

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.karvozavr.hldiffservice.data.auth.User
import ru.karvozavr.hldiffservice.data.auth.UserRepository

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : ReactiveUserDetailsService {

  override fun findByUsername(username: String?): Mono<UserDetails> {
    return if (username == null) {
      Mono.error(UsernameNotFoundException("User not found"))
    } else {
      userRepository.findByUsername(username)
        .switchIfEmpty(Mono.defer { Mono.error<User>(UsernameNotFoundException("User not found")) })
        .map(AuthenticatedUser.Companion::build)
    }
  }
}
