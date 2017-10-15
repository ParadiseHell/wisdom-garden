package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.entity.Plants

/**
 * Created by chengtao on 10/12/17.
 */
fun main(args: Array<String>) {
  var plants: Plants? = null
  val plantsDao: PlantsDao = PlantsDaoImpl()
  plants = plantsDao.createPlants("枫树", "一颗枫树", arrayListOf(1, 2, 3))
  if (plants != null) {
    println(plants)
  }
}