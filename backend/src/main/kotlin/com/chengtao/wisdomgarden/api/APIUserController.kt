package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.API
import com.chengtao.wisdomgarden.APIErrorType
import com.chengtao.wisdomgarden.APIParameters
import com.chengtao.wisdomgarden.db.dao.UserDao
import com.chengtao.wisdomgarden.db.impl.UserDaoImpl
import com.chengtao.wisdomgarden.entity.APIError
import com.chengtao.wisdomgarden.entity.UserType
import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by chengtao on 10/11/17.
 */
@RestController
class APIUserController {
  companion object {
    val userDao: UserDao = UserDaoImpl()
  }

  @PostMapping(API.USER_LOGIN)
  @ResponseBody
  fun login(@RequestParam(value = APIParameters.USER_NAME, required = false) userName: String?,
            @RequestParam(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (!StringUtils.isStringNull(userName, password)) {
      val user = userDao.queryUserByNameAndPassword(userName!!, password!!)
      if (user == null) {
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(APIError(APIErrorType.USER_NAME_OR_PASSWORD_WRONG))
      } else {
        user.password = null
        user.type = null
        user
      }
    } else {
      ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIError(APIErrorType.MISSING_PARAMETER))
    }
  }

  @PostMapping(API.USER_REGISTER)
  @ResponseBody
  fun register(@RequestParam(value = APIParameters.USER_NAME, required = false) userName: String?,
               @RequestParam(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (!StringUtils.isStringNull(userName, password)) {
      if (userDao.isUserExist(userName!!)) {
        ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(APIError(APIErrorType.USER_EXIST))
      } else {
        val user = userDao.createUser(userName, password!!, UserType.COMMON_USER.value)
        if (user == null) {
          ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(APIError(APIErrorType.INTERNAL_SERVER_ERROR))
        } else {
          user.password = null
          user.type = null
          user
        }
      }
    } else {
      ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIError(APIErrorType.MISSING_PARAMETER))
    }
  }
}