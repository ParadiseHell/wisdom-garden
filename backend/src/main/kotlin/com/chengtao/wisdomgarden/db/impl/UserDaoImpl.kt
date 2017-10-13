package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.UserDao
import com.chengtao.wisdomgarden.entity.User
import java.sql.ResultSet

/**
 * Created by chengtao on 10/13/17.
 */
class UserDaoImpl : BaseDaoImpl(), UserDao {
  override fun createUser(userName: String, password: String, type: Int): User {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteUseByUserId(userId: Int): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun isUserExist(userName: String, password: String): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}