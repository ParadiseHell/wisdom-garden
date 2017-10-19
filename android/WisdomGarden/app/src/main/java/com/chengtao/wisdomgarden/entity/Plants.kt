package com.chengtao.wisdomgarden.entity

import java.util.Date

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 10:40 AM
 * Description :
 */
class Plants {
  var plantsId: Int? = null
  var name: String? = null
  var description: String? = null
  var createdAt: Date? = null
  var updatedAt: Date? = null
  var sights: ArrayList<Sight>? = null
  override fun toString(): String =
      "Plants(plantsId=$plantsId, name=$name, description=$description, " +
          "createdAt=$createdAt, updatedAt=$updatedAt, sights=$sights)"
}