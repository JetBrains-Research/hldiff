package ru.karvozavr.hldiffservice.data.auth

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class User(
  @Indexed(unique = true) @NotBlank @Size(max = 30)
  var username: String,

  @NotBlank @Size(max = 120)
  var password: String,

  var roles: MutableSet<Role> = mutableSetOf(Role.USER),

  @Id
  var id: String? = null
)
