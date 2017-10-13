package com.chengtao.wisdomgarden.entity

import java.util.*

/**
 * Created by chengtao on 10/11/17.
 */
class User {
  var userId: Int? = null
  var userName: String? = null
  var password: String? = null
  var type: UserType? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String {
    return "User(userId=$userId, userName=$userName, password=$password, type=$type, createdAt=$createdAt, updatedAt=$updatedAt)"
  }

}

/**
 * 用户类型
 */
@Suppress("unused")
enum class UserType(val typeNumber: Int) {
  VISITOR(-1), COMMON_USER(0), MANAGER(1)
}