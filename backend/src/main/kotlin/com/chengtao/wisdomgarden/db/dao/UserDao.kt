package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.User

/**
 * Created by chengtao on 10/13/17.
 */
interface UserDao {
  fun createUser(userName: String, password: String, type: Int): User
  fun deleteUseByUserId(userId: Int): Boolean
  fun isUserExist(userName: String, password: String): Boolean
}