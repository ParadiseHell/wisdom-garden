package com.chengtao.wisdomgarden.entity

/**
 * Created by chengtao on 10/13/17.
 */
class SightFile {
  var sightId: Int? = null
  var name: Sight? = null
  var category: SightFileCategory? = null
  var url: String? = null
  override fun toString(): String {
    return "SightFile(sightId=$sightId, name=$name, category=$category, url=$url)"
  }

}

enum class SightFileCategory(val value: String) {
  IMAGE("image"), AUDIO("audio"), VIDEO("video")
}