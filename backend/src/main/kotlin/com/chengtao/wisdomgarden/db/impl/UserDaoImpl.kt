package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.UserDao
import com.chengtao.wisdomgarden.entity.User
import com.chengtao.wisdomgarden.entity.UserType
import java.sql.ResultSet

/**
 * Created by chengtao on 10/13/17.
 */
class UserDaoImpl : BaseDaoImpl(), UserDao {
  companion object {
    const val TABLE_NAME = "user"
    const val FIELD_ID = "user_id"
    const val FIELD_USER_NAME = "user_name"
    const val FIELD_PASSWORD = "password"
    const val FIELD_TYPE = "user_type"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME" +
        " ($FIELD_USER_NAME,$FIELD_PASSWORD,$FIELD_TYPE)" +
        " VALUES (?,?,?)"
    const val QUERY_By_NAME_AND_PASSWORD_SQL = "SELECT * FROM $TABLE_NAME" +
        " WHERE $FIELD_USER_NAME = ? AND $FIELD_PASSWORD = ?"
    const val UPDATE_PASSWORD_SQL = "UPDATE $TABLE_NAME SET $FIELD_PASSWORD = ?" +
        " WHERE $FIELD_ID = ?"
  }

  override fun createUser(userName: String, password: String, type: Int): User? {
    val parameters = ArrayList<Any>()
    parameters.add(userName)
    parameters.add(password)
    parameters.add(type)
    if (executeSQL(INSERT_SQL, parameters)) {
      parameters.clear()
      parameters.add(userName)
      parameters.add(password)
      val result: Any? = executeQuery(QUERY_By_NAME_AND_PASSWORD_SQL, parameters)
      if (result != null && result is ArrayList<*> && result.size > 0) {
        return result[0] as User?
      }
    }
    return null
  }

  override fun deleteUserByUserId(userId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_ID, userId)
  }

  override fun isUserExist(userName: String, password: String): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(userName)
    parameters.add(password)
    val isExist: Any? = executeQuery(QUERY_By_NAME_AND_PASSWORD_SQL, parameters, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        return resultSet.next()
      }
    })
    if (isExist is Boolean) {
      return isExist
    }
    return false
  }

  override fun updatePassword(userId: Int, password: String): User? {
    val parameters = ArrayList<Any>()
    parameters.add(password)
    parameters.add(userId)
    if (executeSQL(UPDATE_PASSWORD_SQL, parameters)) {
      return doQueryUserByUserId(userId)
    }
    return null
  }

  override fun queryUserByUserId(userId: Int): User? {
    return doQueryUserByUserId(userId)
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val userList = ArrayList<User>()
      while (resultSet.next()) {
        val user = User()
        user.userId = resultSet.getInt(FIELD_ID)
        user.userName = resultSet.getString(FIELD_USER_NAME)
        user.password = resultSet.getString(FIELD_PASSWORD)
        when (resultSet.getInt(FIELD_TYPE)) {
          UserType.COMMON_USER.value -> user.type = UserType.COMMON_USER
          UserType.MANAGER.value -> user.type = UserType.MANAGER
        }
        user.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        user.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        userList.add(user)
      }
      resultSet.close()
      if (userList.size > 0) {
        return userList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

  private fun doQueryUserByUserId(userId: Int): User? {
    val result: Any? = queryById(TABLE_NAME, FIELD_ID, userId, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        return UserDaoImpl().convertResultSetToAny(resultSet)
      }
    })
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as User?
    }
    return null
  }
}