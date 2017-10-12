package com.chengtao.wisdomgarden.entity
/**
 * Created by chengtao on 10/11/17.
 */
class User {
  var userId: Int? = null
  var userName: String? = null
  var type: UserType? = null
}

/**
 * 用户类型
 */
enum class UserType(val number: Int) {
  VISITOR(-1), COMMON_USER(0), MANAGER(1)
}