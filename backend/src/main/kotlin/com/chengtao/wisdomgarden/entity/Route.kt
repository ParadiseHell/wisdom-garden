package com.chengtao.wisdomgarden.entity

import java.util.*

/**
 * Created by chengtao on 10/15/17.
 */
class Route {
  var routeId: Int? = null
  var name: String? = null
  var description: String? = null
  var sightChain: ArrayList<Sight>? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String {
    return "Route(routeId=$routeId, name=$name, description=$description, sightChain=$sightChain," +
        " createdAt=$createdAt, updatedAt=$updatedAt)"
  }

}