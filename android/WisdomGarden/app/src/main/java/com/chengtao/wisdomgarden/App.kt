package com.chengtao.wisdomgarden

import android.app.Application
import android.util.Log
import com.chengtao.wisdomgarden.utils.ApplicationUtils

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 12:13 AM
 * Description :
 */
class App : Application() {
  override fun onCreate() {
    super.onCreate()
    Log.e("TAG", "Application:onCreate")
    ApplicationUtils.initApplication(this.applicationContext)
  }
}