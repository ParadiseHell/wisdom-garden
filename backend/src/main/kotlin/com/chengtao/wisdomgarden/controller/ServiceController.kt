package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by chengtao on 10/16/17.
 */
@Controller
class ServiceController : BaseController() {
  @GetMapping(Routers.SERVICE)
  fun getSight(): ModelAndView {
    val serviceModelAndView = ModelAndView(Views.SERVICE)
    initMainModelAndView(serviceModelAndView)
    initNavTitle(serviceModelAndView, "服务设施", Routers.SERVICE)
    return serviceModelAndView
  }
}