package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Ecology

/**
 * Created by chengtao on 10/15/17.
 */
interface EcologyDao {
  fun createEcology(sightId: Int, temperature: Float, humidity: Float, pm25: Int,
                    wind: Float, dressing: String, carWashing: String): Ecology?

  fun deleteEcologyBySightId(sightId: Int): Boolean
  fun updateEcology(sightId: Int, temperature: Float?, humidity: Float?, pm25: Int?,
                    wind: Float?, dressing: String?, carWashing: String?): Ecology?

  fun queryEcologyBySightId(sightId: Int): Ecology?
}