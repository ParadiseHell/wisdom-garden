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
}

/**
 * 用户类型
 */
enum class UserType {
  VISITOR(), COMMON_USER(), MANAGER()
}