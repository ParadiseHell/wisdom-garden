package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.*
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
import com.chengtao.wisdomgarden.entity.FileCategory
import com.chengtao.wisdomgarden.entity.SightCateGory
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.isLatitude
import com.chengtao.wisdomgarden.utils.isLongitude
import com.chengtao.wisdomgarden.utils.redirect
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


/**
 * Created by chengtao on 10/16/17.
 */
@Controller
class SightController : BaseController() {
  companion object {
    val sightDao: SightDao = SightDaoImpl()
  }

  @GetMapping(Routers.SIGHT)
  fun getSightView(): ModelAndView {
    val sightAndModelView = ModelAndView(Views.SIGHT)
    initMainModelAndView(sightAndModelView)
    initNavTitle(sightAndModelView, "景点", Routers.SIGHT)
    return sightAndModelView
  }

  @PostMapping(Routers.SIGHT)
  fun createSight(@RequestParam(value = Parameters.SIGHT_NAME) name: String,
                  @RequestParam(value = Parameters.SIGHT_CATEGORY, defaultValue = "-1") category: Int,
                  @RequestParam(value = Parameters.SIGHT_LATITUDE, defaultValue = "360") latitude: Float,
                  @RequestParam(value = Parameters.SIGHT_LONGITUDE, defaultValue = "360") longitude: Float,
                  @RequestParam(value = Parameters.SIGHT_DESCRIPTION) description: String,
                  session: HttpSession): String {
    println("name:$name,category:$category,latitude:$latitude,longitude:$longitude,description:$description")
    if (!StringUtils.isStringNull(name, description) && (category in 0..1) && latitude.isLatitude()
        && longitude.isLongitude()) {
      if (sightDao.querySightByName(name) == null) {
        var canCreate = true
        when (category) {
          SightCateGory.ENTRANCE.value -> {
            if (sightDao.existEntrance()) {
              canCreate = false
              session.setAttribute(Attributes.MESSAGE, Errors.ENTRANCE_SIGHT_EXIST)
            }
          }
          SightCateGory.EXIT.value -> {
            if (sightDao.existExit()) {
              canCreate = false
              session.setAttribute(Attributes.MESSAGE, Errors.EXIT_SIGHT_EXIST)
            }
          }
        }
        if (canCreate) {
          if (sightDao.createSight(category, name, description, latitude, longitude) == null) {
            session.setAttribute(Attributes.MESSAGE, Errors.UNKNOWN_ERROR)
          }
        }
      } else {
        session.setAttribute(Attributes.MESSAGE, Errors.SIGHT_IS_EXIST)
      }
    } else {
      session.setAttribute(Attributes.MESSAGE, Errors.PARAMETERS_ERROR)
    }
    return Routers.SIGHT_CREATE.redirect()
  }

  @GetMapping(Routers.SIGHT_CREATE)
  fun getCreateSightView(): ModelAndView {
    val sightCreateModelView = ModelAndView(Views.SIGHT_CREATE)
    initNavTitle(sightCreateModelView, "创建景点", Routers.SIGHT_CREATE)
    return sightCreateModelView
  }

  @PostMapping(Routers.SIGHT_UPLOAD_FILE)
  @ResponseBody
  fun uploadSightFile(@RequestParam(Parameters.FILE) file: MultipartFile,
                      @RequestParam(Parameters.FILE_CATEGORY) type: String,
                      request: HttpServletRequest): ResponseEntity<Any> {
    return when (type) {
      FileCategory.IMAGE.value -> {
        val fileName = UUID.randomUUID().toString() +
            file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
        val path = request.servletContext.getRealPath(UploadFilePath.UPLOAD_IMAGES)
        val fileDir = File(path)
        if (!fileDir.exists()) {
          fileDir.mkdirs()
        }
        val mFile = File(fileDir, fileName)
        val fos = FileOutputStream(mFile)
        val bos = BufferedOutputStream(fos)
        bos.write(file.bytes)
        return ResponseEntity(HttpStatus.NO_CONTENT)
      }
      FileCategory.VIDEO.value -> ResponseEntity(HttpStatus.NO_CONTENT)
      FileCategory.VIDEO.value -> ResponseEntity(HttpStatus.NO_CONTENT)
      else -> ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}