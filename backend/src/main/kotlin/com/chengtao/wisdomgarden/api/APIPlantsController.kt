package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.API
import com.chengtao.wisdomgarden.APIErrorType
import com.chengtao.wisdomgarden.APIParameters
import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
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
class APIPlantsController : APIBaseController() {
  companion object {
    val plantsDao: PlantsDao = PlantsDaoImpl()
  }

  @GetMapping(API.GET_ALL_PLANTS)
  fun getAllSights(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                   @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    return if (isAuthorized(userName, password)) {
      var plantsList = plantsDao.queryAllPlants()
      if (plantsList == null) {
        plantsList = ArrayList()
      }
      plantsList
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }
}