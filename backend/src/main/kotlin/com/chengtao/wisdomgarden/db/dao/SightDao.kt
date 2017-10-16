package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Sight

/**
 * Created by chengtao on 10/13/17.
 */
interface SightDao {
  fun createSight(type: Int, name: String, description: String, latitude: Float, longitude: Float): Sight?
  fun deleteSightById(id: Int): Boolean
  fun updateSight(id: Int, name: String? = null, description: String? = null, latitude: Float? = null, longitude: Float? = null): Sight?
  fun querySightById(id: Int): Sight?
  fun querySightByName(name: String): Sight?
  fun queryAllSight(): ArrayList<Sight>?
  fun querySightCount(): Int
  fun existEntrance(): Boolean
  fun existExit(): Boolean
}