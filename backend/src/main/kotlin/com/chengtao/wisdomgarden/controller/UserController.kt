package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Index
import com.chengtao.wisdomgarden.Login
import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Register
import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class UserController {
  @RequestMapping(value = Login.PATH, method = arrayOf(RequestMethod.GET))
  fun getLoginView(): String {
    return Login.VIEW
  }

  @RequestMapping(value = Login.PATH, method = arrayOf(RequestMethod.POST))
  fun login(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
            @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String): String {
    if (StringUtils.isStringNull(userName, password)) {
      return Login.VIEW
    }
    return Index.VIEW
  }

  @RequestMapping(value = Register.PATH, method = arrayOf(RequestMethod.GET))
  fun getRegisterView(): String {
    return Register.VIEW
  }

  @RequestMapping(value = Register.PATH, method = arrayOf(RequestMethod.POST))
  fun register(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
               @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String,
               @RequestParam(value = Parameters.CONFIRM_PASSWORD, defaultValue = "") confirmPassword: String): String {
    if (StringUtils.isStringNull(userName, password, confirmPassword)) {
      return Register.VIEW
    }
    if (password != confirmPassword) {
      return Register.VIEW
    }
    return Index.VIEW
  }
}