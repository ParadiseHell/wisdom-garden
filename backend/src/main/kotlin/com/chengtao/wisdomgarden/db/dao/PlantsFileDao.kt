package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.SightFile

/**
 * Created by chengtao on 10/13/17.
 */
interface PlantsFileDao {
  fun insertPlantsFile(plantsId: Int, name: String?, category: String, url: String): Boolean
  fun deletePlantsFile(plantsId: Int, url: String): Boolean
  fun queryPlantsFile(plantsId: Int): MutableList<SightFile>?
}