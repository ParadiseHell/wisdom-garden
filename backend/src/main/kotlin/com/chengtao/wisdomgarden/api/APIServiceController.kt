package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.API
import com.chengtao.wisdomgarden.APIErrorType
import com.chengtao.wisdomgarden.APIParameters
import com.chengtao.wisdomgarden.db.dao.ServiceDao
import com.chengtao.wisdomgarden.db.impl.ServiceDaoImpl
import com.chengtao.wisdomgarden.entity.APIError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

/**
 * Created by chengtao on 10/19/17.
 */
@RestController
class APIServiceController : APIBaseController() {
  companion object {
    val serviceDao: ServiceDao = ServiceDaoImpl()
  }

  @GetMapping(API.GET_ALL_SERVICE_NAME_AND_COUNT)
  fun getAllServicesNameAndCount(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                                 @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (isAuthorized(userName, password)) {
      var serviceNameCountList = serviceDao.queryAllServiceNameAndCount()
      if (serviceNameCountList == null) {
        serviceNameCountList = ArrayList()
      }
      serviceNameCountList
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }

  @GetMapping("${API.SERVICE}/{name}")
  fun getAllServicesByName(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                           @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?,
                           @PathVariable(value = "name") name: String): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (isAuthorized(userName, password)) {
      var serviceList = serviceDao.queryServicesByName(name)
      if (serviceList == null) {
        serviceList = ArrayList()
      }
      serviceList
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }
}