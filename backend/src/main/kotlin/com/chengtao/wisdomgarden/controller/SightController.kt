package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.UploadFilePath
import com.chengtao.wisdomgarden.Views
import com.chengtao.wisdomgarden.entity.FileCategory
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


/**
 * Created by chengtao on 10/16/17.
 */
@Controller
class SightController : BaseController() {
  @GetMapping(Routers.SIGHT)
  fun getSight(): ModelAndView {
    val sightAndModelView = ModelAndView(Views.SIGHT)
    initMainModelAndView(sightAndModelView)
    initNavTitle(sightAndModelView, "景点", Routers.SIGHT)
    return sightAndModelView
  }

  @GetMapping(Routers.SIGHT_CREATE)
  fun createSight(): ModelAndView {
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