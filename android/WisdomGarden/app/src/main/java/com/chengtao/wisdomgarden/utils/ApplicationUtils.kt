package com.chengtao.wisdomgarden.utils

import android.app.Application

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 6:03 PM
 * Description :
 */
object ApplicationUtils {
  var appliction: Application? = null
  fun initApplication(application: Application) {
    this.appliction = appliction
  }
}