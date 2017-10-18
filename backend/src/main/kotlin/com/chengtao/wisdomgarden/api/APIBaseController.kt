package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.db.dao.UserDao
import com.chengtao.wisdomgarden.db.impl.UserDaoImpl
import com.chengtao.wisdomgarden.utils.StringUtils

/**
 * Created by chengtao on 10/19/17.
 */
open class APIBaseController {
  companion object {
    val userDao: UserDao = UserDaoImpl()
  }

  fun isAuthorized(userName: String?, password: String?): Boolean {
    if (!StringUtils.isStringNull(userName, password)) {
      if (userDao.queryUserByNameAndPassword(userName!!, password!!) != null) {
        return true
      }
    }
    return false
  }
}