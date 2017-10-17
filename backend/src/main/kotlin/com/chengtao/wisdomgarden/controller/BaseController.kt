package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Attributes
import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.dao.RouteDao
import com.chengtao.wisdomgarden.db.dao.ServiceDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.db.impl.RouteDaoImpl
import com.chengtao.wisdomgarden.db.impl.ServiceDaoImpl
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpSession

/**
 * Created by chengtao on 10/17/17.
 */
open class BaseController {
  companion object {
    val sightDao: SightDao = SightDaoImpl()
    val plantsDao: PlantsDao = PlantsDaoImpl()
    val routeDao: RouteDao = RouteDaoImpl()
    val serviceDao: ServiceDao = ServiceDaoImpl()
  }

  protected fun initMainModelAndView(modelAndView: ModelAndView) {
    modelAndView.addObject(Attributes.SIGHT_COUNT, sightDao.querySightCount())
    modelAndView.addObject(Attributes.PLANTS_COUNT, plantsDao.queryPlantsCount())
    modelAndView.addObject(Attributes.ROUTE_COUNT, routeDao.queryAllRoutesCount())
    modelAndView.addObject(Attributes.SERVICE_COUNT, serviceDao.queryAllServicesCount())
  }

  protected fun initNavTitle(modelAndView: ModelAndView, currentView: String, viewRoute: String) {
    modelAndView.addObject(Attributes.CURRENT_VIEW, currentView)
    modelAndView.addObject(Attributes.VIEW_ROUTER, viewRoute)
  }

  protected fun addErrorMessage(session: HttpSession, message: String) {
    session.setAttribute(Attributes.ERROR_MESSAGE, message)
  }

  protected fun addSuccessMessage(session: HttpSession, message: String) {
    session.setAttribute(Attributes.SUCCESS_MESSAGE, message)
  }
}