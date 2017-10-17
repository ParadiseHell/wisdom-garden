package com.chengtao.wisdomgarden.entity

import java.util.*

/**
 * Created by chengtao on 10/13/17.
 */
class Sight {
  var id: Int? = null
  var type: SightCateGory? = null
  var name: String? = null
  var description: String? = null
  var latitude: Float? = null
  var longitude: Float? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  var files: ArrayList<SightFile>? = null
  var ecology: Ecology? = null
  override fun toString(): String {
    return "Sight(id=$id, name=$name, description=$description, latitude=$latitude, longitude=$longitude, " +
        "createdAt=$createdAt, updatedAt=$updatedAt, files=$files, ecology=$ecology)"
  }
}

enum class SightCateGory(val value: Int) {
  OTHER(0), ENTRANCE(1), EXIT(2)
}