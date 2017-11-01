package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.dao.SightFileDao
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
import com.chengtao.wisdomgarden.db.impl.SightFileDaoImpl
import com.chengtao.wisdomgarden.entity.FileCategory
import com.chengtao.wisdomgarden.entity.SightCateGory
import com.chengtao.wisdomgarden.entity.ViewAndRouter
import com.chengtao.wisdomgarden.utils.*
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
class SightController : BaseController() {
  companion object {
    val sightDao: SightDao = SightDaoImpl()
    val sightFileDao: SightFileDao = SightFileDaoImpl()
  }

  @GetMapping(Routers.SIGHT)
  fun getSightView(): ModelAndView {
    val modelAndView = ModelAndView(Views.SIGHT)
    initMainModelAndView(modelAndView)
    initNavTitle(modelAndView, "景点", Routers.SIGHT)
    modelAndView.addObject(Attributes.SIGHT_LIST, sightDao.queryAllSight())
    return modelAndView
  }

  @GetMapping("${Routers.SIGHT}/{id}")
  fun getSightDetailView(@PathVariable(value = "id") id: Int): ModelAndView {
    val modelAndView = ModelAndView(Views.SIGHT_DETAIL)
    val viewAndRouterList = ArrayList<ViewAndRouter>()
    viewAndRouterList.add(ViewAndRouter("景点", Routers.SIGHT))
    val sight = sightDao.querySightById(id)
    if (sight != null) {
      modelAndView.addObject(Attributes.SIGHT, sight)
      viewAndRouterList.add(ViewAndRouter(sight.name!!, "${Routers.SIGHT}/$id"))
    }
    modelAndView.addObject(Attributes.VIEW_AND_ROUTER, viewAndRouterList)
    return modelAndView
  }

  @GetMapping(Routers.SIGHT_EDIT)
  fun getCreateSightView(): ModelAndView {
    val sightCreateModelView = ModelAndView(Views.SIGHT_EDIT)
    initNavTitle(sightCreateModelView, "创建景点", Routers.SIGHT_EDIT)
    return sightCreateModelView
  }

  @PostMapping(Routers.SIGHT)
  fun createSight(@RequestParam(value = Parameters.NAME) name: String,
                  @RequestParam(value = Parameters.CATEGORY, defaultValue = "-1") category: Int,
                  @RequestParam(value = Parameters.LATITUDE, defaultValue = "360") latitude: Float,
                  @RequestParam(value = Parameters.LONGITUDE, defaultValue = "360") longitude: Float,
                  @RequestParam(value = Parameters.DESCRIPTION) description: String,
                  session: HttpSession): String {
    println("name:$name,category:$category,latitude:$latitude,longitude:$longitude,description:$description")
    if (!StringUtils.isStringNull(name, description) && (category in 0..2) && latitude.isLatitude()
        && longitude.isLongitude()) {
      if (sightDao.querySightByName(name) == null) {
        var canCreate = true
        when (category) {
          SightCateGory.ENTRANCE.value -> {
            if (sightDao.existEntrance()) {
              canCreate = false
              addErrorMessage(session, "入口已存在")
            }
          }
          SightCateGory.EXIT.value -> {
            if (sightDao.existExit()) {
              canCreate = false
              addErrorMessage(session, "出口已存在")
            }
          }
        }
        if (canCreate) {
          if (sightDao.createSight(category, name, description, latitude, longitude) == null) {
            addErrorMessage(session, Errors.UNKNOWN_ERROR)
          } else {
            addSuccessMessage(session, "创建景点成功")
          }
        }
      } else {
        addErrorMessage(session, "景点已存在")
      }
    } else {
      addErrorMessage(session, Errors.PARAMETERS_ERROR)
    }
    return Routers.SIGHT_EDIT.redirect()
  }

  @PostMapping("${Routers.SIGHT}/{id}${Routers.UPLOAD}")
  @ResponseBody
  fun uploadSightFile(@PathVariable("id") id: Int, @RequestParam(Parameters.FILE) file: MultipartFile,
                      @RequestParam(Parameters.FILE_CATEGORY) type: String,
                      request: HttpServletRequest): ResponseEntity<Any> {
    var array: Array<String>? = null
    var category: FileCategory? = null
    when (type) {
      FileCategory.IMAGE.value -> {
        category = FileCategory.IMAGE
        array = FileUtils.saveFile(file, UploadFilePath.UPLOAD_IMAGES)
      }
      FileCategory.VIDEO.value -> {
        category = FileCategory.VIDEO
        array = FileUtils.saveFile(file, UploadFilePath.UPLOAD_VIDEO)
      }
      FileCategory.AUDIO.value -> {
        category = FileCategory.AUDIO
        array = FileUtils.saveFile(file, UploadFilePath.UPLOAD_AUDIO)
      }
    }
    var isSuccess = false
    if (array != null && array.size == 2 && category != null) {
      isSuccess = sightFileDao.insertSightFile(id, array[0], category.value, array[1])
    }
    return if (isSuccess) {
      ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    } else {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
  }

  @GetMapping("${Routers.SIGHT_DELETE}/{id}")
  fun deleteSightById(@PathVariable("id") id: Int): String {
    sightDao.deleteSightById(id)
    return Routers.SIGHT.redirect()
  }

  @GetMapping("/test")
  fun test(): String {
    return "test"
  }
}