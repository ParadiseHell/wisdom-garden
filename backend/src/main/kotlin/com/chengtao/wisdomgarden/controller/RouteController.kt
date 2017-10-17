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
class RouteController : BaseController() {
  @GetMapping(Routers.ROUTE)
  fun getSight(): ModelAndView {
    val routeModelAndView = ModelAndView(Views.ROUTE)
    initMainModelAndView(routeModelAndView)
    initNavTitle(routeModelAndView, "路线", Routers.ROUTE)
    return routeModelAndView
  }
}