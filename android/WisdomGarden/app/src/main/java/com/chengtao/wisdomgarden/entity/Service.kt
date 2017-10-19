package com.chengtao.wisdomgarden.entity

import java.util.Date

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 10:45 AM
 * Description :
 */
class Service {
  var serviceId: Int? = null
  var name: String? = null
  var latitude: Float? = null
  var longitude: Float? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String =
      "Service(serviceId=$serviceId, name=$name, latitude=$latitude, longitude=$longitude, " +
          "createdAt=$createdAt, updatedAt=$updatedAt)"
}