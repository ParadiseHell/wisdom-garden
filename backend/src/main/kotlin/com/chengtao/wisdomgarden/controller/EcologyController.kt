package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Attributes
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView

/**
 * Created by chengtao on 10/18/17.
 */
@Controller
class EcologyController : BaseController() {
  @GetMapping("${Routers.SIGHT}/{id}${Routers.ECOLOGY}")
  fun getEcologyCrateView(@PathVariable(value = "id") sightId: String): ModelAndView {
    val modelAndView = ModelAndView(Views.ECOLOGY_CREATE)
    modelAndView.addObject(Attributes.SIGHT_ID, sightId)
    initNavTitle(modelAndView, "创建生态", "${Routers.SIGHT}/$sightId${Routers.ECOLOGY}")
    return modelAndView
  }
}