package com.chengtao.wisdomgarden.utils

import android.content.Context

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 6:03 PM
 * Description :
 */
class ApplicationUtils {
  companion object {
    var applicationContext: Context? = null
    fun initApplication(applicationContext: Context) {
      this.applicationContext = applicationContext
    }
  }
}