package com.chengtao.wisdomgarden.api

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 10:53 AM
 * Description :
 */
interface ErrorString {
  companion object {
    const val MISSING_PARAMETER = "missing parameter"
    const val INTERNAL_SERVER_ERROR = "internal server error"
    const val UNAUTHORIZED = "unauthorized"
    //用户
    const val USER_NOT_EXIST = "user not exist"
    const val USER_EXIST = "user exist"
  }
}

interface ErrorType {
  companion object {
    const val UNAUTHORIZED: Short = 0
    const val USER_NOT_EXIST: Short = 1
    const val USER_EXIST: Short = 2
  }
}