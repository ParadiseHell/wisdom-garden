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
  var images: ArrayList<SightFile>? = null
  var videos: ArrayList<SightFile>? = null
  var audios: ArrayList<SightFile>? = null
  var ecology: Ecology? = null
  override fun toString(): String {
    return "Sight(id=$id, type=$type, name=$name, description=$description," +
        " latitude=$latitude, longitude=$longitude, " +
        "createdAt=$createdAt, updatedAt=$updatedAt, " +
        "images=$images, videos=$videos, audios=$audios, ecology=$ecology)"
  }

}

enum class SightCateGory(val value: Int) {
  OTHER(0), ENTRANCE(1), EXIT(2)
}