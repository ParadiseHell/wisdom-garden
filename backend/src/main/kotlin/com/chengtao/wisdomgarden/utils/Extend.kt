package com.chengtao.wisdomgarden.utils

/**
 * Created by chengtao on 10/13/17.
 */
fun String.redirect(): String {
  return "redirect:" + this
}

/**
 * 判断纬度
 */
fun Float.isLatitude(): Boolean {
  return this >= -90f && this <= 90f
}

/**
 * 判断经度
 */
fun Float.isLongitude(): Boolean {
  return this >= -180f && this <= 180f
}