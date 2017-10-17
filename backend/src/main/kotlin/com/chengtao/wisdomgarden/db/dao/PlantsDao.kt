package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Plants

/**
 * Created by chengtao on 10/13/17.
 */
interface PlantsDao {
  fun createPlants(name: String, description: String): Plants?
  fun deletePlantsById(plantsId: Int): Boolean
  fun updatePlants(plantsId: Int, name: String?, description: String?): Plants?
  fun queryPlantsById(plantsId: Int): Plants?
  fun queryPlantsByName(name: String): Plants?
  fun queryAllPlants(): ArrayList<Plants>?
  fun queryPlantsCount(): Int
}