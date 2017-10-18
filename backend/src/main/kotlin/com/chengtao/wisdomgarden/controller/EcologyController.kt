package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.EcologyDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.EcologyDaoImpl
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
 * Created by chengtao on 10/18/17.
 */
@Controller
class EcologyController : BaseController() {
  companion object {
    val ecologyDao: EcologyDao = EcologyDaoImpl()
    val sightDao: SightDao = SightDaoImpl()
  }

  @GetMapping("${Routers.SIGHT}/{id}${Routers.ECOLOGY}")
  fun getEcologyCrateView(@PathVariable(value = "id") sightId: Int): ModelAndView {
    val modelAndView = ModelAndView(Views.ECOLOGY_EDIT)
    modelAndView.addObject(Attributes.SIGHT_ID, sightId)
    val ecology = ecologyDao.queryEcologyBySightId(sightId)
    val viewAndRouter = ArrayList<ViewAndRouter>()
    val sight = sightDao.querySightById(sightId)
    if (sight != null) {
      viewAndRouter.add(ViewAndRouter("景点", Routers.SIGHT))
      viewAndRouter.add(ViewAndRouter(sight.name!!, "${Routers.SIGHT}/$sightId"))
    }
    if (ecology == null) {
      viewAndRouter.add(ViewAndRouter("创建生态", "${Routers.SIGHT}/$sightId${Routers.ECOLOGY}"))
    } else {
      viewAndRouter.add(ViewAndRouter("更新生态", "${Routers.SIGHT}/$sightId${Routers.ECOLOGY}"))
    }
    modelAndView.addObject(Attributes.ECOLOGY, ecology)
    modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewAndRouter)
    return modelAndView
  }

  @PostMapping(Routers.ECOLOGY)
  fun createEcology(@RequestParam(value = Parameters.SIGHT_ID, defaultValue = "-1") sightId: Int,
                    @RequestParam(value = Parameters.TEMPERATURE, defaultValue = "-1000") temperature: Float,
                    @RequestParam(value = Parameters.HUMIDITY, defaultValue = "-1") humidity: Float,
                    @RequestParam(value = Parameters.PM25, defaultValue = "-1") pm25: Int,
                    @RequestParam(value = Parameters.WIND) wind: String,
                    @RequestParam(value = Parameters.DRESSING) dressing: String,
                    session: HttpSession): String {
    if (sightId == -1) {
      Routers.SIGHT.redirect()
    }
    if (sightId > 0 && (temperature in -100..100) && (humidity in 0..1) && (pm25 in 0..500)
        && !StringUtils.isStringNull(wind, dressing)) {
      if (ecologyDao.queryEcologyBySightId(sightId) == null) {
        if (ecologyDao.createEcology(sightId, temperature, humidity, pm25, wind, dressing) != null) {
          addSuccessMessage(session, "创建生态成功")
          return "${Routers.SIGHT}/$sightId".redirect()
        } else {
          addErrorMessage(session, Errors.UNKNOWN_ERROR)
        }
      } else {
        if (ecologyDao.updateEcology(sightId, temperature, humidity, pm25, wind, dressing) != null) {
          addSuccessMessage(session, "更新生态成功")
          return "${Routers.SIGHT}/$sightId".redirect()
        } else {
          addErrorMessage(session, Errors.UNKNOWN_ERROR)
        }
      }
    } else {
      addErrorMessage(session, Errors.PARAMETERS_ERROR)
    }
    return "${Routers.SIGHT}/$sightId${Routers.ECOLOGY}".redirect()
  }
}