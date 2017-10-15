package com.chengtao.wisdomgarden.entity

import java.util.*

/**
 * Created by chengtao on 10/15/17.
 */
class Service {
  var serviceId: Int? = null
  var name: String? = null
  var latitude: Float? = null
  var longitude: Float? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String {
    return "Service(serviceId=$serviceId, name=$name, latitude=$latitude, longitude=$longitude, createdAt=$createdAt, updatedAt=$updatedAt)"
  }

}