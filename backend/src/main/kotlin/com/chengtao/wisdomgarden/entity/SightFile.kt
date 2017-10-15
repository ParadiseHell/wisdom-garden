package com.chengtao.wisdomgarden.entity

/**
 * Created by chengtao on 10/13/17.
 */
class SightFile {
  var sightId: Int? = null
  var name: String? = null
  var category: FileCategory? = null
  var url: String? = null
  override fun toString(): String {
    return "SightFile(sightId=$sightId, name=$name, category=$category, url=$url)"
  }
}