package com.chengtao.wisdomgarden.entity

import java.util.Date

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:26 AM
 * Description :
 */
class User {
  var userId: Int? = null
  var userName: String? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String =
      "User(userId=$userId, userName=$userName, createdAt=$createdAt, updatedAt=$updatedAt)"

}