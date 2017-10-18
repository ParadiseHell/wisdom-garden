package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
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
    val sightDao: SightDao = SightDaoImpl()
  }

  @GetMapping(Routers.PLANTS)
  fun getPlantsView(): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "植物", Routers.PLANTS)
    modelAndView.addObject(Attributes.PLANTS_LIST, plantsDao.queryAllPlants())
    return modelAndView
  }

  @GetMapping(Routers.PLANTS_CREATE)
  fun getPlantsCreateView(): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS_CREATE)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "创建植物", Routers.PLANTS_CREATE)
    modelAndView.addObject(Attributes.SIGHT_LIST, sightDao.queryAllSight())
    return modelAndView
  }

  @PostMapping(Routers.PLANTS)
  fun createPlants(@RequestParam(value = Parameters.NAME) name: String,
                   @RequestParam(value = Parameters.DESCRIPTION) description: String,
                   @RequestParam(value = Parameters.SIGHT_IDS) sightIds: String,
                   session: HttpSession): String {
    println("name:$name,description:$description,sightIds:$sightIds")
    if (!StringUtils.isStringNull(name, description)) {
      var sightIdsList: ArrayList<Int>? = null
      if (sightIds != "") {
        sightIdsList = ArrayList()
        val list = sightIds.split(",")
        list.forEach {
          sightIdsList!!.add(it.toInt())
        }
      }
      if (plantsDao.queryPlantsByName(name) == null) {
        if (plantsDao.createPlants(name, description, sightIdsList) != null) {
          addSuccessMessage(session, "创建植物成功")
        } else {
          addErrorMessage(session, Errors.UNKNOWN_ERROR)
        }
      } else {
        addErrorMessage(session, "植物已存在")
      }
    } else {
      addErrorMessage(session, Errors.PARAMETERS_ERROR)
    }
    return Routers.PLANTS_CREATE.redirect()
  }
}