package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.dao.PlantsFileDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.db.impl.PlantsFileDaoImpl
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
import com.chengtao.wisdomgarden.entity.ViewAndRouter
import com.chengtao.wisdomgarden.utils.FileUtils
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.redirect
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * Created by chengtao on 10/16/17.
 */
@Controller
class PlantsController : BaseController() {
  companion object {
    val plantsDao: PlantsDao = PlantsDaoImpl()
    val sightDao: SightDao = SightDaoImpl()
    val plantsFileDao: PlantsFileDao = PlantsFileDaoImpl()
  }

  @GetMapping(Routers.PLANTS)
  fun getPlantsView(): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "植物", Routers.PLANTS)
    modelAndView.addObject(Attributes.PLANTS_LIST, plantsDao.queryAllPlants())
    return modelAndView
  }

  @GetMapping("${Routers.PLANTS}/{id}")
  fun getPlantsDetailView(@PathVariable(value = "id") id: Int): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS_DETAIL)
    val viewAndRouterList = ArrayList<ViewAndRouter>()
    viewAndRouterList.add(ViewAndRouter("植物", Routers.PLANTS))
    val plants = plantsDao.queryPlantsById(id)
    if (plants != null) {
      viewAndRouterList.add(ViewAndRouter(plants.name!!, "${Routers.PLANTS}/$id"))
      modelAndView.addObject(Attributes.PLANTS, plants)
    }
    modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewAndRouterList)
    return modelAndView
  }

  @GetMapping(Routers.PLANTS_EDIT)
  fun getPlantsCreateView(): ModelAndView {
    val modelAndView = ModelAndView(Views.PLANTS_EDIT)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "创建植物", Routers.PLANTS_EDIT)
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
    return Routers.PLANTS_EDIT.redirect()
  }

  @GetMapping("${Routers.PLANTS_DELETE}/{id}")
  fun deleteSightById(@PathVariable("id") id: Int): String {
    plantsDao.deletePlantsById(id)
    return Routers.PLANTS.redirect()
  }

  @PostMapping("${Routers.PLANTS}/{id}${Routers.UPLOAD}")
  @ResponseBody
  fun uploadSightFile(@PathVariable("id") id: Int, @RequestParam(Parameters.FILE) file: MultipartFile,
                      @RequestParam(Parameters.FILE_CATEGORY) category: String,
                      request: HttpServletRequest): ResponseEntity<Any> {
    val array: Array<String>? = FileUtils.saveFile(file, category)
    var isSuccess = false
    if (array != null && array.size == 2) {
      isSuccess = plantsFileDao.insertPlantsFile(id, array[0], category, array[1])
    }
    return if (isSuccess) {
      ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    } else {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
  }
}