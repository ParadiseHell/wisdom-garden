package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.RouteDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.RouteDaoImpl
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
import com.chengtao.wisdomgarden.entity.ViewAndRouter
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.redirect
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

  @GetMapping("${Routers.ROUTE}/{id}")
  fun getRouteDetailView(@PathVariable(value = "id") id: Int): ModelAndView {
    val route = routeDao.queryRouteById(id)
    val modelAndView = ModelAndView(Views.ROUTE_DETAIL)
    if (route != null) {
      val viewRouterList = ArrayList<ViewAndRouter>()
      viewRouterList.add(ViewAndRouter("线路", Routers.ROUTE))
      viewRouterList.add(ViewAndRouter(route.name!!, "${Routers.ROUTE}/$id"))
      modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewRouterList)
      modelAndView.addObject(Attributes.ROUTE, route)
    }
    return modelAndView
  }

  @PostMapping(Routers.ROUTE)
  fun createRoute(@RequestParam(value = Parameters.NAME) name: String,
                  @RequestParam(value = Parameters.DESCRIPTION) description: String,
                  @RequestParam(value = Parameters.SIGHT_IDS) sightIds: String,
                  session: HttpSession): String {
    if (!StringUtils.isStringNull(name, description, sightIds)) {
      if (routeDao.queryRouteByName(name) == null) {
        val sightIdsList = ArrayList<Int>()
        val list = sightIds.split(",")
        list.forEach {
          if (it != "") {
            sightIdsList.add(it.toInt())
          }
        }
        if (list.size > 1) {
          if (routeDao.createRoute(name, description, sightIdsList) != null) {
            addSuccessMessage(session, "创建线路成功")
          } else {
            addErrorMessage(session, Errors.UNKNOWN_ERROR)
          }
        } else {
          addErrorMessage(session, "路线至少包含两个景点")
        }
      } else {
        addErrorMessage(session, Errors.PARAMETERS_ERROR)
      }
    } else {
      addErrorMessage(session, "路线名已存在")
    }
    return Routers.ROUTE_EDIT.redirect()
  }

  @PostMapping("${Routers.ROUTE}/{id}")
  fun updateRoute(
      @PathVariable("id") id: Int,
      @RequestParam(value = Parameters.NAME) name: String,
      @RequestParam(value = Parameters.DESCRIPTION) description: String,
      @RequestParam(value = Parameters.SIGHT_IDS) sightIds: String,
      session: HttpSession): String {
    val route = routeDao.queryRouteById(id)
    if (route == null) {
      addErrorMessage(session, "该路线已不存在")
      return Routers.ROUTE.redirect()
    }
    if (sightDao.existEntrance() && sightDao.existExit()) {
      if (!StringUtils.isStringNull(name, description, sightIds)) {
        val tempRoute = routeDao.queryRouteByName(name)
        if (tempRoute == null || tempRoute.name == route.name) {
          val sightIdsList = ArrayList<Int>()
          val list = sightIds.split(",")
          list.forEach {
            if (it != "") {
              sightIdsList.add(it.toInt())
            }
          }
          if (sightIdsList[0] == sightDao.queryEntranceSightId() &&
              sightIdsList[sightIdsList.size - 1] == sightDao.queryExitSightId()) {
            if (routeDao.updateRoute(id, name, description, sightIdsList) != null) {
              addSuccessMessage(session, "更新线路成功")
            } else {
              addErrorMessage(session, Errors.UNKNOWN_ERROR)
            }
          } else {
            addErrorMessage(session, "线路的开始必须为入口,线路的结尾必须为出口")
          }
        } else {
          addErrorMessage(session, "路线名已存在")
        }
      } else {
        addErrorMessage(session, Errors.PARAMETERS_ERROR)
      }
    } else {
      addErrorMessage(session, "还没有创建入口景点和出口景点")
      return Routers.SIGHT_EDIT.redirect()
    }
    return "${Routers.ROUTE}/{id}${Routers.UPDATE}".redirect()
  }

  @GetMapping("${Routers.ROUTE}/{id}${Routers.UPDATE}")
  fun getUpdateRouteView(@PathVariable("id") id: Int, session: HttpSession): Any {
    val modelAndView: ModelAndView
    val route = routeDao.queryRouteById(id)
    return if (route != null) {
      modelAndView = ModelAndView(Views.ROUTE_EDIT)
      val viewAndRouter = ArrayList<ViewAndRouter>()
      viewAndRouter.add(ViewAndRouter("路线", Routers.ROUTE))
      viewAndRouter.add(ViewAndRouter(route.name!!, "${Routers.ROUTE}/$id"))
      viewAndRouter.add(ViewAndRouter("更新", "${Routers.ROUTE}/$id${Routers.UPDATE}"))
      modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewAndRouter)
      modelAndView.addObject(Attributes.ROUTE, route)
      modelAndView.addObject(Attributes.SIGHT_LIST, sightDao.queryAllSight())
      modelAndView
    } else {
      addErrorMessage(session, "该路线已不存在")
      Routers.ROUTE.redirect()
    }
  }

  @GetMapping(Routers.ROUTE_EDIT)
  fun getRouteEditView(): ModelAndView {
    val modelAndView = ModelAndView(Views.ROUTE_EDIT)
    initNavTitle(modelAndView, "创建路线", Routers.ROUTE_EDIT)
    modelAndView.addObject(Attributes.SIGHT_LIST, sightDao.queryAllSight())
    return modelAndView
  }

  @GetMapping("${Routers.ROUTE_DELETE}/{id}")
  fun deleteSightById(@PathVariable("id") id: Int): String {
    routeDao.deleteRouteById(id)
    return Routers.ROUTE.redirect()
  }

}