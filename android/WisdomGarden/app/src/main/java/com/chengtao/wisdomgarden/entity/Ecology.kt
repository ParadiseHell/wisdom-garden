package com.chengtao.wisdomgarden.entity

import java.util.Date

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 4:36 PM
 * Description :
 */
class Ecology {
  var sightId: Int? = null
  var temperature: Float? = null
  var humidity: Float? = null
  var pm25: Int? = null
  var wind: String? = null
  var dressing: String? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String =
      "Ecology(sightId=$sightId, temperature=$temperature, humidity=$humidity, pm25=$pm25, " +
          "wind=$wind, dressing=$dressing,  createdAt=$createdAt, updatedAt=$updatedAt)"
}