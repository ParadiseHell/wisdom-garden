package com.chengtao.wisdomgarden.utils

import android.content.Context
import android.util.Log

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 6:03 PM
 * Description :
 */
class ApplicationUtils {
  companion object {
    var appliction: Context? = null
    fun initApplication(applicationContext: Context) {
      appliction = applicationContext
      Log.e("TAG", appliction.toString())
    }
  }
}