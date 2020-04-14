package ru.karvozavr.hldiffservice.security

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.karvozavr.hldiffservice.data.auth.User
import kotlin.streams.toList


class AuthenticatedUser(
  private val username: String,
  @JsonIgnore private val password: String,
  private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {

  companion object {
    fun build(user: User): AuthenticatedUser {
      val authorities: MutableList<GrantedAuthority> = user.roles.stream()
        .map { role -> SimpleGrantedAuthority(role.name) }
        .toList().toMutableList()

      return AuthenticatedUser(user.username, user.password, authorities)
    }
  }

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    return authorities
  }

  override fun isEnabled(): Boolean {
    return true
  }

  override fun getUsername(): String {
    return username
  }

  override fun getPassword(): String {
    return password
  }

  override fun isCredentialsNonExpired(): Boolean {
    return true
  }

  override fun isAccountNonExpired(): Boolean {
    return true
  }

  override fun isAccountNonLocked(): Boolean {
    return true
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other === null || this::class != other::class) return false;
    return username == (other as? AuthenticatedUser)?.username
  }

  override fun hashCode(): Int {
    return username.hashCode()
  }
}
