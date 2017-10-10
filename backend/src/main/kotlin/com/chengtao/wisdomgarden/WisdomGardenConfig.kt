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

interface Cookies {
  companion object {
    const val USER_NAME: String = "user_name"
    const val PASSWORD: String = "password"
  }
}

interface Parameters {
  companion object {
    const val USER_NAME: String = "user_name"
    const val PASSWORD: String = "password"
    const val CONFIRM_PASSWORD = "confirm_password"
  }
}

//视图
interface Index {
  companion object {
    const val VIEW: String = "index"
  }
}

interface Login {
  companion object {
    const val VIEW: String = "login"
    const val PATH: String = "/login"
    const val TITLE: String = "登录"
    const val USER_NAME: String = "用户名"
    const val PLACEHOLDER_USER_NAME: String = "请输入用户名"
    const val PASSWORD: String = "密码"
    const val PLACEHOLDER_PASSWORD: String = "请输入密码"
    const val REGISTER_ACCOUNT: String = "注册账号"
  }
}

interface Register {
  companion object {
    const val VIEW: String = "register"
    const val PATH: String = "/register"
    const val TITLE: String = "注册"
    const val USER_NAME: String = "用户名"
    const val PLACEHOLDER_USER_NAME: String = "请输入用户名"
    const val PASSWORD: String = "密码"
    const val PLACEHOLDER_PASSWORD: String = "请输入密码"
    const val CONFIRM_PASSWORD: String = "密码"
    const val PLACEHOLDER_CONFIRM_PASSWORD: String = "请输入密码"
  }
}