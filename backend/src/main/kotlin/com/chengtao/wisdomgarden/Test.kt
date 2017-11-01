package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl

/**
 * Created by chengtao on 10/12/17.
 */
fun main(args: Array<String>) {
  val plantsDao: PlantsDao = PlantsDaoImpl()
  val plants = plantsDao.queryPlantsById(1)
  if (plants != null) {
    println("plants:$plants")
  }
}