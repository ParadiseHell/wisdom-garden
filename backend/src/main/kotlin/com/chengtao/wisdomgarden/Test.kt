package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.impl.SightDaoImpl

/**
 * Created by chengtao on 10/12/17.
 */
fun main(args: Array<String>) {
  val sightDaoImpl: SightDao = SightDaoImpl()
  //sightDaoImpl.createSight("北京林业大学", "中国林学第一首府", 63f, 63f)
  sightDaoImpl.updateSight(1,"北京林业大学","中国林学第一首府",63f,63f)
}