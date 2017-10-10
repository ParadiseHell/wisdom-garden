package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Cookies
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class IndexController {
  @RequestMapping(value = Routers.INDEX, method = arrayOf(RequestMethod.GET))
  fun index(@CookieValue(value = Cookies.USER_NAME, defaultValue = "") userName: String,
            @CookieValue(value = Cookies.PASSWORD, defaultValue = "") password: String): String {
    if (StringUtils.isStringNull(userName, password)) {
      return Views.LOGIN
    }
    return Views.REGISTER
  }
}