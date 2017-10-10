package com.chengtao.wisdomgarden

/**
 * Created by chengtao on 10/10/17.
 */
interface WisdomGardenConfig {
  companion object {
    const val APPLICATION_NAME: String = "智慧园林"
    const val AUTHOR: String = "程涛"
  }
}

//Cookies
interface Cookies {
  companion object {
    const val USER_NAME: String = "user_name"
    const val PASSWORD: String = "password"
  }
}

//请求参数
interface Parameters {
  companion object {
    const val USER_NAME: String = "user_name"
    const val PASSWORD: String = "password"
    const val CONFIRM_PASSWORD = "confirm_password"
  }
}

//路由
interface Routers {
  companion object {
    const val INDEX: String = "/"
    const val LOGIN: String = "/login"
    const val REGISTER: String = "/register"
    const val STATICS: String = "/statics"
    const val API: String = "/api"
  }
}

//视图
interface Views {
  companion object {
    const val INDEX: String = "index"
    const val LOGIN: String = "login"
    const val REGISTER: String = "register"
  }
}