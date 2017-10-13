package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.SightFile

/**
 * Created by chengtao on 10/13/17.
 */
interface SightFileDao {
  fun insertSightFile(sightId: Int, name: String?, category: String, url: String): Boolean
  fun deleteSightFile(sightId: Int, url: String): Boolean
  fun querySightFile(sightId: Int): MutableList<SightFile>?
}