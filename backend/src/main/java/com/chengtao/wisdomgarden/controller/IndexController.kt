package com.chengtao.wisdomgarden.controller

import StringUtils
import WisdomGardenConfig
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {
    @RequestMapping("/")
    fun index(@CookieValue(value = WisdomGardenConfig.Cookies.USER_NAME, defaultValue = "")
              userName: String,
              @CookieValue(value = WisdomGardenConfig.Cookies.PASSWORD, defaultValue = "")
              password: String,
              model: Model): String {
        if (StringUtils.isStringNull(userName, password)) {
            model.addAttribute(WisdomGardenConfig.TITLE, WisdomGardenConfig.View.Login.TITLE)
            return WisdomGardenConfig.View.Login.VIEW
        }
        model.addAttribute(WisdomGardenConfig.TITLE, WisdomGardenConfig.View.Index.TITLE)
        return WisdomGardenConfig.View.Index.VIEW
    }
}