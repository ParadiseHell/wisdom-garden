package com.chengtao.wisdomgarden

/**
 * Created by chengtao on 10/10/17.
 */
interface ApplicationConfig {
  companion object {
    const val APPLICATION_NAME = "智慧园林"
    const val AUTHOR = "程涛"
  }
}

//Cookies
interface Cookies {
  companion object {
    const val COOKIES_EXPIRATION_DATE = 60 * 60 * 24
    const val USER_NAME = "user_name"
    const val PASSWORD = "password"
  }
}

//请求参数
interface Parameters {
  companion object {
    const val USER_NAME = "user_name"
    const val PASSWORD = "password"
    const val CONFIRM_PASSWORD = "confirm_password"
  }
}

//属性
interface Attributes{
  companion object {
    const val MESSAGE = "message"
  }
}

//路由
interface Routers {
  companion object {
    //页面
    const val INDEX = "/"
    const val LOGIN = "/login"
    const val REGISTER = "/register"
    const val SIGHT = "/sight"
    const val PLANTS = "/plants"
    const val ROUTE = "/route"
    const val SERVICE = "/service"
    //静态资源
    const val STATICS = "/statics"
    //API
    const val API = "/api"
    //其他
    const val LOGOUT = "/logout"
  }
}

//视图
interface Views {
  companion object {
    const val INDEX = "index"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val SIGHT = "sight"
    const val PLANTS = "plants"
    const val ROUTE = "route"
    const val SERVICE = "service"
  }
}