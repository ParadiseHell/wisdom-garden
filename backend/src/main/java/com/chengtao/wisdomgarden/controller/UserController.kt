package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.entity.User
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
  @RequestMapping(
      value = "/user/{UserId}",
      method = arrayOf(RequestMethod.GET),
      produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
  )
  fun getUser(@PathVariable("UserId") id: Int): User {
    return User(id, "ChengTao")
  }

  @RequestMapping(
      value = "/user",
      method = arrayOf(RequestMethod.GET),
      produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
  )
  fun getUser(): User {
    return User((Math.random() * 1000).toInt(), "ChengTao")
  }
}