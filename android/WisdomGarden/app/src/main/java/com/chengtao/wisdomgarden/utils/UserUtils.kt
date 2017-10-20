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
    println("saveUser")
    if (ApplicationUtils.applicationContext != null) {
      println("doSaveUser")
      SharedPreferencesUtils.putAsynchronous(ApplicationUtils.applicationContext!!, USER_FILE,
          USER_NAME,
          name)
      SharedPreferencesUtils.putAsynchronous(ApplicationUtils.applicationContext!!, USER_FILE,
          USER_PASSWORD,
          password)
    }
  }

  fun getCurrentUserName(): String? {
    return if (ApplicationUtils.applicationContext != null) {
      SharedPreferencesUtils.getString(ApplicationUtils.applicationContext!!, USER_FILE, USER_NAME)
    } else {
      null
    }
  }

  fun getCurrentUserPassword(): String? {
    return if (ApplicationUtils.applicationContext != null) {
      SharedPreferencesUtils.getString(ApplicationUtils.applicationContext!!, USER_FILE,
          USER_PASSWORD)
    } else {
      null
    }
  }

  fun clean() {
    if (ApplicationUtils.applicationContext != null) {
      SharedPreferencesUtils.clean(ApplicationUtils.applicationContext!!, USER_FILE)
    }
  }

  fun isUserExist(): Boolean = !StringUtils.isStringNull(getCurrentUserName(),
      getCurrentUserPassword())
}