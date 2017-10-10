package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.entity.User
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by chengtao on 10/11/17.
 */
@RestController
class APIUserController {
  @RequestMapping("/api/{name}")
  fun test(@PathVariable(value = "name") name: String): User {
    return User(name)
  }
}