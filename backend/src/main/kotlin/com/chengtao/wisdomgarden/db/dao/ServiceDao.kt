package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Service
import com.chengtao.wisdomgarden.entity.ServiceNameAndCount

/**
 * Created by chengtao on 10/16/17.
 */
interface ServiceDao {
  fun createService(name: String, latitude: Float, longitude: Float): Service?
  fun deleteServiceById(serviceId: Int): Boolean
  fun updateService(serviceId: Int, name: String?, latitude: Float?, longitude: Float?): Service?
  fun queryByServiceId(serviceId: Int): Service?
  fun queryServiceByLocation(latitude: Float, longitude: Float, range: Float): ArrayList<Service>?
  fun queryService(name: String, latitude: Float, longitude: Float): Service?
  fun queryServicesByName(name: String): ArrayList<Service>?
  fun queryAllServiceNameAndCount(): ArrayList<ServiceNameAndCount>?
  fun queryAllServicesCount(): Int
}