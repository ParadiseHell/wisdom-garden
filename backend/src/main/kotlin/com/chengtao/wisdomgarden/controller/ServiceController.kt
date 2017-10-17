package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Errors
import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import com.chengtao.wisdomgarden.db.dao.ServiceDao
import com.chengtao.wisdomgarden.db.impl.ServiceDaoImpl
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.isLatitude
import com.chengtao.wisdomgarden.utils.isLongitude
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
class ServiceController : BaseController() {
  companion object {
    val serviceDao: ServiceDao = ServiceDaoImpl()
  }

  @GetMapping(Routers.SERVICE)
  fun getServiceView(): ModelAndView {
    val modelAndView = ModelAndView(Views.SERVICE)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "服务设施", Routers.SERVICE)
    return modelAndView
  }

  @GetMapping(Routers.SERVICE_CREATE)
  fun getServiceCreateView(): ModelAndView {
    val modelAndView = ModelAndView(Views.SERVICE_CREATE)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "创建服务设施", Routers.SERVICE_CREATE)
    return modelAndView
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
    return Routers.SERVICE_CREATE.redirect()
  }
}