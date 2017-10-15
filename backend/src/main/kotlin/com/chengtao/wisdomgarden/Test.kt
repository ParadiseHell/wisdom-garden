package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.dao.PlantsFileDao
import com.chengtao.wisdomgarden.db.impl.PlantsDaoImpl
import com.chengtao.wisdomgarden.db.impl.PlantsFileDaoImpl
import com.chengtao.wisdomgarden.entity.FileCategory
import com.chengtao.wisdomgarden.entity.Plants

/**
 * Created by chengtao on 10/12/17.
 */
fun main(args: Array<String>) {
  var plants: Plants? = null
  val plantsDao: PlantsDao = PlantsDaoImpl()
 /* plants = plantsDao.createPlants("大树", "一颗大树", arrayListOf(4, 5))
  if (plants != null) {
    println(plants)
  }*/
  /*val plantsFileDao : PlantsFileDao = PlantsFileDaoImpl()
  plantsFileDao.insertPlantsFile(6,"b",FileCategory.IMAGE.value,"b")*/
  /*plants = plantsDao.queryPlantsById(6)
  if (plants != null) {
    println(plants)
  }*/
  if (plantsDao.deletePlantsById(6)){
    println("success")
  }
  /*val plantList = plantsDao.queryAllPlants()
  if (plantList != null){
    println(plantList)
  }*/
  /*val sightList = ArrayList<Int>()
  sightList.add(4)
  sightList.add(5)
  plants = plantsDao.updatePlants(2, "鲜花", "一朵鲜花", sightList)
  if (plants != null) {
    println(plants)
  }*/
}