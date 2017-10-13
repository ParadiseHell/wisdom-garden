package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class IndexController {
  @RequestMapping(value = Routers.INDEX, method = arrayOf(RequestMethod.GET))
  fun getIndex(): String {
    return Views.INDEX
  }
}