package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.redirect
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpSession

/**
 * Created by chengtao on 10/16/17.
 */
@Controller
class PlantsController : BaseController() {
  companion object {
    val plantsDao: PlantsDao = PlantsDaoImpl()
  }

  @GetMapping(Routers.PLANTS)
  fun getPlantsView(): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "植物", Routers.PLANTS)
    return modelAndView
  }

  @GetMapping(Routers.PLANTS_CREATE)
  fun getPlantsCreateView(): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS_CREATE)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "植物", Routers.PLANTS_CREATE)
    return modelAndView
  }

  @PostMapping(Routers.PLANTS)
  fun createPlants(@RequestParam(value = Parameters.PLANTS_NAME) name: String,
                   @RequestParam(value = Parameters.PLANTS_DESCRIPTION) description: String,
                   session: HttpSession): String {
    println("name:$name,description:$description")
    if (!StringUtils.isStringNull(name, description)) {
      if (plantsDao.queryPlantsByName(name) == null) {
        if (plantsDao.createPlants(name, description) != null) {
          session.setAttribute(Attributes.SUCCESS_MESSAGE, "创建植物成功")
        } else {
          session.setAttribute(Attributes.ERROR_MESSAGE, Errors.UNKNOWN_ERROR)
        }
      } else {
        session.setAttribute(Attributes.ERROR_MESSAGE, Errors.PLANTS_IS_EXIST)
      }
    } else {
      session.setAttribute(Attributes.ERROR_MESSAGE, Errors.PARAMETERS_ERROR)
    }
    return Routers.PLANTS_CREATE.redirect()
  }
}