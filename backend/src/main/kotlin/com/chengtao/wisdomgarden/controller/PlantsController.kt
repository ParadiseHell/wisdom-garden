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
class PlantsController : BaseController() {
  @GetMapping(Routers.PLANTS)
  fun getSight(): ModelAndView {
    val plantsModelAndView = ModelAndView(Views.PLANTS)
    initMainModelAndView(plantsModelAndView)
    initNavTitle(plantsModelAndView,"植物",Routers.PLANTS)
    return plantsModelAndView
  }
}