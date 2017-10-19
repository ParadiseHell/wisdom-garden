package com.chengtao.wisdomgarden.utils
import java.security.MessageDigest

/**
 * MS5
 */
object MD5Util {
  fun md5(txt: String): String? {
    try {
      val md = MessageDigest.getInstance("MD5")
      val digested = md.digest(txt.toByteArray(Charsets.UTF_8))
      return digested.joinToString("") {
        String.format("%02x", it)
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return null
  }
}
