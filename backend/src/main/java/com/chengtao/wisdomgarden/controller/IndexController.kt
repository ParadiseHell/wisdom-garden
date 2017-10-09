package com.chengtao.wisdomgarden.controller

import StringUtils
import com.chengtao.wisdomgarden.Cookies
import com.chengtao.wisdomgarden.Index
import com.chengtao.wisdomgarden.Login
import com.chengtao.wisdomgarden.WisdomGardenConfig
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {
  @RequestMapping("/")
  fun index(@CookieValue(value = Cookies.USER_NAME, defaultValue = "")
            userName: String,
            @CookieValue(value = Cookies.PASSWORD, defaultValue = "")
            password: String,
            model: Model): String {
    if (StringUtils.isStringNull(userName, password)) {
      model.addAttribute(WisdomGardenConfig.TITLE, Login.TITLE)
      return Login.VIEW
    }
    model.addAttribute(WisdomGardenConfig.TITLE, Index.TITLE)
    return Index.VIEW
  }
}