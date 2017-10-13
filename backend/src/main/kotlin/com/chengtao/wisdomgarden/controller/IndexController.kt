package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class IndexController {
  @GetMapping(Routers.INDEX)
  fun getIndex(): String {
    println("getIndex")
    return Views.INDEX
  }
}