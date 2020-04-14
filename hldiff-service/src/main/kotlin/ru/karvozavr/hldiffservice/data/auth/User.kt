package ru.karvozavr.hldiffservice.data.auth

import org.springframework.data.annotation.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class User(
  @Id @NotBlank @Size(max = 30)
  var username: String,

  @NotBlank @Size(max = 120)
  var password: String,

  var roles: MutableSet<Role> = mutableSetOf(Role.ROLE_USER)
)
