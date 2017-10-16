package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Sight

/**
 * Created by chengtao on 10/14/17.
 */
interface PlantsToSightDao {
  fun insertPlantsSights(plantsId: Int, sightIds: ArrayList<Int>): Boolean
  fun deletePlantsSight(plantsId: Int, sightId: Int): Boolean
  fun deleteAllPlantsSight(plantsId: Int): Boolean
  fun deletePlantsAllSights(sightId: Int): Boolean
  fun updatePlantsSights(plantsId: Int, sightIds: ArrayList<Int>): Boolean
  fun queryPlantsAllSights(plantsId: Int): ArrayList<Sight>?
}