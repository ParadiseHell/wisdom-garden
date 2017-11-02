package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.API
import com.chengtao.wisdomgarden.APIErrorType
import com.chengtao.wisdomgarden.APIParameters
import com.chengtao.wisdomgarden.db.dao.RouteDao
import com.chengtao.wisdomgarden.db.impl.RouteDaoImpl
import com.chengtao.wisdomgarden.entity.APIError
import com.chengtao.wisdomgarden.entity.Route
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
class APIRouteController : APIBaseController() {
  companion object {
    val routeDao: RouteDao = RouteDaoImpl()
  }

  @GetMapping(API.GET_ALL_ROUTES)
  fun getAllRoutes(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                   @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (isAuthorized(userName, password)) {
      var routeList = routeDao.queryAllRoutes()
      if (routeList == null) {
        routeList = ArrayList()
      }
      routeList
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }

  @GetMapping("${API.ROUTE}/{id}")
  fun getRouteById(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                   @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?,
                   @PathVariable("id") id: Int): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (isAuthorized(userName, password)) {
      var route = routeDao.queryRouteById(id)
      if (route == null) {
        route = Route()
      }
      route
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }
}