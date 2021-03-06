package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.ServiceDao
import com.chengtao.wisdomgarden.db.impl.ServiceDaoImpl
import com.chengtao.wisdomgarden.entity.ViewAndRouter
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.isLatitude
import com.chengtao.wisdomgarden.utils.isLongitude
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
class ServiceController : BaseController() {
  companion object {
    val serviceDao: ServiceDao = ServiceDaoImpl()
  }

  @GetMapping(Routers.SERVICE)
  fun getServiceView(): ModelAndView {
    val modelAndView = ModelAndView(Views.SERVICE)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "服务设施", Routers.SERVICE)
    modelAndView.addObject(Attributes.SERVICE_NAME_COUNT_LIST, serviceDao.queryAllServiceNameAndCount())
    return modelAndView
  }

  @GetMapping("${Routers.SERVICE}/{name}")
  fun getServiceNameListView(@PathVariable(value = "name") name: String): ModelAndView {
    val modelAndView = ModelAndView(Views.SERVICE_DETAIL)
    val viewAndRouterList = ArrayList<ViewAndRouter>()
    viewAndRouterList.add(ViewAndRouter("服务设施", Routers.SERVICE))
    viewAndRouterList.add(ViewAndRouter(name, "${Routers.SERVICE}/$name"))
    modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewAndRouterList)
    modelAndView.addObject(Attributes.SERVICE_LIST, serviceDao.queryServicesByName(name))
    return modelAndView
  }

  @GetMapping(Routers.SERVICE_EDIT)
  fun getServiceCreateView(): ModelAndView {
    val modelAndView = ModelAndView(Views.SERVICE_EDIT)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "创建服务设施", Routers.SERVICE_EDIT)
    return modelAndView
  }

  @GetMapping("${Routers.SERVICE}/{id}${Routers.UPDATE}")
  fun getServiceUpdateView(@PathVariable("id") id: Int, session: HttpSession): Any {
    val modelAndView: ModelAndView
    val service = serviceDao.queryByServiceId(id)
    return if (service != null) {
      modelAndView = ModelAndView(Views.SERVICE_EDIT)
      val viewAndRouter = ArrayList<ViewAndRouter>()
      viewAndRouter.add(ViewAndRouter("服务设施", Routers.SERVICE))
      viewAndRouter.add(ViewAndRouter(service.name!!, "${Routers.SERVICE}/${service.name!!}"))
      viewAndRouter.add(ViewAndRouter("更新", "${Routers.SERVICE}/$id${Routers.UPDATE}"))
      modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewAndRouter)
      modelAndView.addObject(Attributes.SERVICE, service)
      modelAndView
    } else {
      addErrorMessage(session, "该服务设施已不存在")
      Routers.SERVICE.redirect()
    }
  }

  @PostMapping(Routers.SERVICE)
  fun createService(@RequestParam(value = Parameters.NAME) name: String,
                    @RequestParam(value = Parameters.LATITUDE) latitude: Float,
                    @RequestParam(value = Parameters.LONGITUDE) longitude: Float,
                    session: HttpSession): String {
    if (!StringUtils.isStringNull(name) && latitude.isLatitude() && longitude.isLongitude()) {
      if (serviceDao.queryService(name, latitude, longitude) == null) {
        if (serviceDao.createService(name, latitude, longitude) != null) {
          addSuccessMessage(session, "创建服务设施成功")
        } else {
          addErrorMessage(session, Errors.UNKNOWN_ERROR)
        }
      } else {
        addErrorMessage(session, "服务设施已存在")
      }
    } else {
      addErrorMessage(session, Errors.PARAMETERS_ERROR)
    }
    return Routers.SERVICE_EDIT.redirect()
  }

  @PostMapping("${Routers.SERVICE}/{id}")
  fun updateService(
      @PathVariable("id") id: Int,
      @RequestParam(value = Parameters.NAME) name: String,
      @RequestParam(value = Parameters.LATITUDE) latitude: Float,
      @RequestParam(value = Parameters.LONGITUDE) longitude: Float,
      session: HttpSession): String {
    val service = serviceDao.queryByServiceId(id)
    if (service == null) {
      addErrorMessage(session, "该服务设施已不存在")
      return Routers.SERVICE.redirect()
    }
    if (!StringUtils.isStringNull(name) && latitude.isLatitude() && longitude.isLongitude()) {
      if (serviceDao.queryService(name, latitude, longitude) == null) {
        if (serviceDao.updateService(id, name, latitude, longitude) != null) {
          addSuccessMessage(session, "更新服务设施成功")
        } else {
          addErrorMessage(session, Errors.UNKNOWN_ERROR)
        }
      } else {
        addErrorMessage(session, "该位置已有相同服务设施")
      }
    } else {
      addErrorMessage(session, Errors.PARAMETERS_ERROR)
    }
    return "${Routers.SERVICE}/$id${Routers.UPDATE}".redirect()
  }

  @GetMapping("${Routers.SERVICE_DELETE}/{id}")
  fun deleteSightById(@PathVariable("id") id: Int): String {
    serviceDao.deleteServiceById(id)
    return Routers.SERVICE.redirect()
  }
}