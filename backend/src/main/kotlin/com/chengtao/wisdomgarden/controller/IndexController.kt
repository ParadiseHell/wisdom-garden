package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Cookies
import com.chengtao.wisdomgarden.Index
import com.chengtao.wisdomgarden.Login
import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class IndexController {
  @RequestMapping("/")
  fun index(@CookieValue(value = Cookies.USER_NAME, defaultValue = "") userName: String,
            @CookieValue(value = Cookies.PASSWORD, defaultValue = "") password: String): String {
    if (StringUtils.isStringNull(userName, password)) {
      return Login.VIEW
    }
    return Index.VIEW
  }
}