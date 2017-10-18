package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.RouteDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.RouteDaoImpl
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
class RouteController : BaseController() {
  companion object {
    val routeDao: RouteDao = RouteDaoImpl()
    val sightDao: SightDao = SightDaoImpl()
  }

  @GetMapping(Routers.ROUTE)
  fun getRouteView(): ModelAndView {
    val modelAndView = ModelAndView(Views.ROUTE)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "路线", Routers.ROUTE)
    modelAndView.addObject(Attributes.ROUTE_LIST, routeDao.queryAllRoutes())
    return modelAndView
  }

  @PostMapping(Routers.ROUTE)
  fun createRoute(@RequestParam(value = Parameters.NAME) name: String,
                  @RequestParam(value = Parameters.DESCRIPTION) description: String,
                  @RequestParam(value = Parameters.SIGHT_IDS) sightIds: String,
                  session: HttpSession): String {
    if (sightDao.existEntrance() && sightDao.existExit()) {
      if (!StringUtils.isStringNull(name, description, sightIds)) {
        val sightIdsList = ArrayList<Int>()
        val list = sightIds.split(",")
        list.forEach {
          sightIdsList.add(it.toInt())
        }
        if (sightIdsList[0] == sightDao.queryEntranceSightId() &&
            sightIdsList[sightIdsList.size - 1] == sightDao.queryExitSightId()) {
          if (routeDao.createRoute(name, description, sightIdsList) != null) {
            addSuccessMessage(session, "创建线路成功")
          } else {
            addErrorMessage(session, Errors.UNKNOWN_ERROR)
          }
        } else {
          addErrorMessage(session, "线路的开始必须为入口,线路的结尾必须为出口")
        }
      } else {
        addErrorMessage(session, Errors.PARAMETERS_ERROR)
      }
    } else {
      addErrorMessage(session, "还没有创建入口景点和出口景点")
      return Routers.INDEX.redirect()
    }
    return Routers.ROUTE_EDIT.redirect()
  }

  @GetMapping(Routers.ROUTE_EDIT)
  fun getRouteEditView(): ModelAndView {
    val modelAndView = ModelAndView(Views.ROUTE_EDIT)
    initNavTitle(modelAndView, "创建路线", Routers.ROUTE_EDIT)
    modelAndView.addObject(Attributes.SIGHT_LIST, sightDao.queryAllSight())
    return modelAndView
  }

}