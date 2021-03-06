package com.chengtao.wisdomgarden.db.dao

import com.chengtao.wisdomgarden.entity.Route

/**
 * Created by chengtao on 10/15/17.
 */
interface RouteDao {
  fun createRoute(name: String, description: String, sightIds: ArrayList<Int>): Route?
  fun deleteRouteById(routeId: Int): Boolean
  fun deleteAllRoutes(): Boolean
  fun updateRoute(routeId: Int, name: String?, description: String?, sightIds: ArrayList<Int>?): Route?
  fun queryRouteById(routeId: Int): Route?
  fun queryRouteByName(name: String): Route?
  fun queryAllRoutes(): ArrayList<Route>?
  fun queryAllRoutesCount(): Int
}