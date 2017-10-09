package com.chengtao.wisdomgarden

interface WisdomGardenConfig {
  companion object {
    const val TITLE: String = "title"
    const val APPLICATION_NAME: String = "智慧园林"
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
  }
}

interface Index {
  companion object {
    const val VIEW: String = "index"
    const val TITLE: String = WisdomGardenConfig.APPLICATION_NAME
  }
}

interface Login {
  companion object {
    const val PATH: String = "login"
    const val VIEW: String = "login"
    const val TITLE: String = "登录"
    const val LABEL_USER_NAME: String = "用户名"
    const val PLACEHOLDER_USER_NAME: String = "请输入用户名"
    const val LABEL_PASSWORD: String = "密码"
    const val PLACEHOLDER_PASSWORD: String = "请输入密码"
  }
}

interface Register {
  companion object {
    const val PATH: String = "register"
    const val VIEW: String = "register"
  }
}