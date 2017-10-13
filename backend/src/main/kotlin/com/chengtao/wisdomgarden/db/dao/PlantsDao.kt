package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Plants

/**
 * Created by chengtao on 10/13/17.
 */
interface PlantsDao {
  fun createPlants(name: String, description: String, sightIds: MutableList<Int>): Plants?
  fun deletePlants(plantsId: Int): Boolean
  fun updatePlants(name: String?, description: String?, sightIds: MutableList<Int>?): Plants?
  fun queryPlantsById(plantsId: Int): Plants?
  fun queryAllPlants(): MutableList<Plants>?
}