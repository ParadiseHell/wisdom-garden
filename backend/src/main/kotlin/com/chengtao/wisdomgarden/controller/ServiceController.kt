package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Created by chengtao on 10/16/17.
 */
@Controller
class ServiceController {
  @GetMapping(Routers.SERVICE)
  fun getSight(): String {
    return Views.SERVICE
  }
}