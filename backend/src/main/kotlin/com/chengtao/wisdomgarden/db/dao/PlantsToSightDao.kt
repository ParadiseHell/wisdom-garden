package com.chengtao.wisdomgarden.db.dao

/**
 * Created by chengtao on 10/14/17.
 */
interface PlantsToSightDao {
  fun insertPlantsSights(plantsId: Int, sightIds: MutableList<Int>): Boolean
  fun deltePlantsSight(plantsId: Int, sightId: Int): Boolean
  fun updatePlantsSights(plantsId: Int, sightIds: MutableList<Int>): Boolean
}