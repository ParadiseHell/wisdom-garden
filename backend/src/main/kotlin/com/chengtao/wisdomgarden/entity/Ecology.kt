package com.chengtao.wisdomgarden.entity

import java.util.*

/**
 * Created by chengtao on 10/15/17.
 */
class Ecology {
  var sightId: Int? = null
  var temperature: Float? = null
  var humidity: Float? = null
  var pm25: Int? = null
  var wind: Float? = null
  var dressing: String? = null
  var carWashing: String? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String {
    return "Ecology(sightId=$sightId, temperature=$temperature, humidity=$humidity, pm25=$pm25, " +
        "wind=$wind, dressing=$dressing, carWashing=$carWashing, createdAt=$createdAt, updatedAt=$updatedAt)"
  }

}