package com.chengtao.wisdomgarden.utils

/**
 * Created by chengtao on 10/10/17.
 */
class StringUtils {
  companion object {
    fun isStringNull(neededString: String, vararg stringArray: String): Boolean {
      if (neededString == null || neededString == "") {
        return true
      }
      return stringArray.any { it == null || it == "" }
    }
  }
}