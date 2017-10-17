package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class IndexController : BaseController() {
  @GetMapping(Routers.INDEX)
  fun getIndex(): ModelAndView {
    val indexModelAndView = ModelAndView(Views.INDEX)
    initMainModelAndView(indexModelAndView)
    return indexModelAndView
  }
}