package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Login
import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Register
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserController {
  @RequestMapping(method = arrayOf(RequestMethod.POST), value = Login.PATH)
  fun login(@RequestParam(value = Parameters.USER_NAME) userName: String,
            @RequestParam(value = Parameters.PASSWORD) password: String): String {
    return ""
  }

  @RequestMapping(method = arrayOf(RequestMethod.POST), value = Register.PATH)
  fun register(@RequestParam(value = Parameters.USER_NAME) userName: String,
               @RequestParam(value = Parameters.PASSWORD) password: String): String {
    return ""
  }
}