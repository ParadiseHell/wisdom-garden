package com.chengtao.wisdomgarden.entity

import java.util.ArrayList
import java.util.Date

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 10:37 AM
 * Description :
 */
class Sight {
  var id: Int? = null
  var name: String? = null
  var description: String? = null
  var latitude: Float? = null
  var longitude: Float? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  var ecology: Ecology? = null
  var images: ArrayList<WisdomGardenFile>? = null
  var videos: ArrayList<WisdomGardenFile>? = null
  var audios: ArrayList<WisdomGardenFile>? = null
  override fun toString(): String {
    return "Sight(id=$id, name=$name, description=$description, " +
        "latitude=$latitude, longitude=$longitude, createdAt=$createdAt, updatedAt=$updatedAt, " +
        "ecology=$ecology, images=$images, videos=$videos, audios=$audios)"
  }

}