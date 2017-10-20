package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.API
import com.chengtao.wisdomgarden.APIErrorType
import com.chengtao.wisdomgarden.APIParameters
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl
import com.chengtao.wisdomgarden.entity.APIError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

/**
 * Created by chengtao on 10/19/17.
 */
@RestController
class APISightController : APIBaseController() {
  companion object {
    val sightDao: SightDao = SightDaoImpl()
  }

  @GetMapping(API.GET_ALL_SIGHTS)
  fun getAllSights(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                   @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (isAuthorized(userName, password)) {
      var sightList = sightDao.queryAllSight()
      if (sightList == null) {
        sightList = ArrayList()
      }
      sightList
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }
}