package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Service

/**
 * Created by chengtao on 10/16/17.
 */
interface ServiceDao {
  fun createService(name: String, latitude: Float, longitude: Float): Service?
  fun delteServiceById(serviceId: Int): Boolean
  fun updateService(serviceId: Int, name: String?, latitude: Float?, longitude: Float?): Service?
  fun queryServiceByLocation(latitude: Float, longitude: Float): ArrayList<Service>?
}