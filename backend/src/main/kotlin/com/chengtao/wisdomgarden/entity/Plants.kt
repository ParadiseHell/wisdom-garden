package com.chengtao.wisdomgarden.entity

import java.util.*

/**
 * Created by chengtao on 10/13/17.
 */
class Plants {
  var plantsId: Int? = null
  var name: String? = null
  var description: String? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  var sights: MutableList<Sight>? = null
  var files: MutableList<PlantsFile>? = null
  override fun toString(): String {
    return "Plants(plantsId=$plantsId, name=$name, description=$description, " +
        "createdAt=$createdAt, updatedAt=$updatedAt, sights=$sights, files=$files)"
  }

}