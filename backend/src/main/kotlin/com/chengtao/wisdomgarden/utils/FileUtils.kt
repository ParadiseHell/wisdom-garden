package com.chengtao.wisdomgarden.utils

import com.chengtao.wisdomgarden.UploadFilePath
import com.chengtao.wisdomgarden.entity.FileCategory
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Created by chengtao on 11/1/17.
 */
object FileUtils {
  fun saveFile(file: MultipartFile, type: String): Array<String>? {
    var fileSubDirectory: String? = null
    when (type) {
      FileCategory.IMAGE.value -> {
        fileSubDirectory = UploadFilePath.UPLOAD_IMAGES
      }
      FileCategory.VIDEO.value -> {
        fileSubDirectory = UploadFilePath.UPLOAD_VIDEO
      }
      FileCategory.AUDIO.value -> {
        fileSubDirectory = UploadFilePath.UPLOAD_AUDIO
      }
    }
    if (fileSubDirectory != null) {
      try {
        val fileName = UUID.randomUUID().toString() +
            file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
        val fileDir = File(UploadFilePath.UPLOAD_DIRECTORY + fileSubDirectory)
        var canSaveFile: Boolean? = false
        if (!fileDir.exists()) {
          if (!fileDir.mkdirs()) {
            println("FileUtils.saveFile-->create directory fail")
          } else {
            canSaveFile = true
            println("FileUtils.saveFile-->create directory success")
          }
        } else {
          canSaveFile = true
          println("FileUtils.saveFile-->directory is already exist")
        }
        if (canSaveFile!!) {
          val mFile = File(fileDir, fileName)
          val fos = FileOutputStream(mFile)
          val bos = BufferedOutputStream(fos)
          bos.write(file.bytes)
          val url = UploadFilePath.REAL_PATH + fileSubDirectory + File.separator + fileName
          val result = arrayOf(fileName, url)
          println("save file success:$result")
          return result
        }
      } catch (e: Exception) {
        println("FileUtils.saveFile-->e:${e.message}")
      }
    }
    return null
  }
}