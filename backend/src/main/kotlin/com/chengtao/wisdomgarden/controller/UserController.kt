package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
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
  @RequestMapping(value = Routers.LOGIN, method = arrayOf(RequestMethod.GET))
  fun getLoginView(): String {
    return Views.LOGIN
  }

  @RequestMapping(value = Routers.LOGIN, method = arrayOf(RequestMethod.POST))
  fun login(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
            @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String): String {
    if (StringUtils.isStringNull(userName, password)) {
      return Views.LOGIN
    }
    return Views.INDEX
  }

  @RequestMapping(value = Routers.REGISTER, method = arrayOf(RequestMethod.GET))
  fun getRegisterView(): String {
    return Views.REGISTER
  }

  @RequestMapping(value = Routers.REGISTER, method = arrayOf(RequestMethod.POST))
  fun register(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
               @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String,
               @RequestParam(value = Parameters.CONFIRM_PASSWORD, defaultValue = "") confirmPassword: String): String {
    if (StringUtils.isStringNull(userName, password, confirmPassword)) {
      return Views.REGISTER
    }
    if (password != confirmPassword) {
      return Views.REGISTER
    }
    return Views.INDEX
  }
}