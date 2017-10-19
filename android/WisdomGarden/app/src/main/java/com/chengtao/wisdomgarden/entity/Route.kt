package com.chengtao.wisdomgarden.entity

import java.util.Date

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 10:40 AM
 * Description :
 */
class Route {
  var routeId: Int? = null
  var name: String? = null
  var description: String? = null
  var sightChain: ArrayList<Sight>? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  override fun toString(): String =
      "Route(routeId=$routeId, name=$name, description=$description, sightChain=$sightChain," +
          " createdAt=$createdAt, updatedAt=$updatedAt)"

}