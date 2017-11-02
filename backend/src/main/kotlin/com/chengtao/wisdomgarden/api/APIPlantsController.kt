package com.chengtao.wisdomgarden.api

import com.chengtao.wisdomgarden.API
import com.chengtao.wisdomgarden.APIErrorType
import com.chengtao.wisdomgarden.APIParameters
import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.entity.APIError
import com.chengtao.wisdomgarden.entity.Plants
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
class APIPlantsController : APIBaseController() {
  companion object {
    val plantsDao: PlantsDao = PlantsDaoImpl()
  }

  @GetMapping(API.GET_ALL_PLANTS)
  fun getAllPlants(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                   @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
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

  @GetMapping("${API.PLANT}/{id}")
  fun getPlantById(@RequestHeader(value = APIParameters.USER_NAME, required = false) userName: String?,
                   @RequestHeader(value = APIParameters.PASSWORD, required = false) password: String?,
                   @PathVariable("id") id: Int): Any? {
    println("userName{${APIParameters.USER_NAME}}:$userName")
    println("password{${APIParameters.PASSWORD}}:$password")
    return if (isAuthorized(userName, password)) {
      var plant = plantsDao.queryPlantsById(id)
      if (plant == null) {
        plant = Plants()
      }
      plant
    } else {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIError(APIErrorType.UNAUTHORIZED))
    }
  }
}