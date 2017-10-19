package com.chengtao.wisdomgarden.utils

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 5:58 PM
 * Description :
 */
object UserUtils {
  private const val USER_FILE = "USER_FILE"
  private const val USER_NAME = "USER_NAME"
  private const val USER_PASSWORD = "USER_PASSWORD"
  fun saveUser(name: String, password: String) {
    if (ApplicationUtils.appliction != null) {
      SharedPreferencesUtils.putAsynchronous(ApplicationUtils.appliction!!, USER_FILE, USER_NAME,
          name)
      SharedPreferencesUtils.putAsynchronous(ApplicationUtils.appliction!!, USER_FILE,
          USER_PASSWORD,
          password)
    }
  }

  fun getCurrentUserName(): String? {
    return if (ApplicationUtils.appliction != null) {
      SharedPreferencesUtils.getString(ApplicationUtils.appliction!!, USER_FILE, USER_NAME)
    } else {
      null
    }
  }

  fun getCurrentUserPassword(): String? {
    return if (ApplicationUtils.appliction != null) {
      SharedPreferencesUtils.getString(ApplicationUtils.appliction!!, USER_FILE, USER_PASSWORD)
    } else {
      null
    }
  }
}