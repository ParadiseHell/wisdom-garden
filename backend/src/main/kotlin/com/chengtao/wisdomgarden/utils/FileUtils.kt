package com.chengtao.wisdomgarden.utils

import com.chengtao.wisdomgarden.UploadFilePath
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Created by chengtao on 11/1/17.
 */
object FileUtils {
  fun saveFile(file: MultipartFile, fileSubDirectory: String): String? {
    try {
      val fileName = UUID.randomUUID().toString() +
          file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
      val fileDir = File(UploadFilePath.UPLOAD_DIRECTORY + fileSubDirectory)
      if (!fileDir.exists()) {
        fileDir.mkdirs()
      }
      val mFile = File(fileDir, fileName)
      val fos = FileOutputStream(mFile)
      val bos = BufferedOutputStream(fos)
      bos.write(file.bytes)
      return UploadFilePath.REAL_PATH + fileSubDirectory + File.separator + fileName
    } catch (e: Exception) {
      println("FileUtils.saveFile-->e:${e.message}")
    }
    return null
  }
}