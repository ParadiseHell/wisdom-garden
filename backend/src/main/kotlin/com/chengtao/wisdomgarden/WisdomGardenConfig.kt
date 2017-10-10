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

//视图
interface Index {
  companion object {
    const val VIEW: String = "index"
  }
}

interface Login {
  companion object {
    const val VIEW: String = "login"
  }
}