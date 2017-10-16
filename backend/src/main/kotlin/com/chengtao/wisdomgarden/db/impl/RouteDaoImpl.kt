package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.RouteDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.entity.Route
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.utils.StringUtils
import java.sql.ResultSet

/**
 * Created by chengtao on 10/16/17.
 */
class RouteDaoImpl : BaseDaoImpl(), RouteDao {
  companion object {
    const val TABLE_NAME = "route"
    const val FIELD_ID = "id"
    const val FIELD_NAME = "name"
    const val FIELD_DESCRIPTION = "description"
    const val FIELD_SIGHT_ID_CHAIN = "sight_id_chain"
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_NAME,$FIELD_DESCRIPTION,$FIELD_SIGHT_ID_CHAIN)" +
        " VALUES (?,?,?)"
    const val QUERY_ROUTE_BY_NAME = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ? LIMIT 1"
  }

  override fun createRoute(name: String, description: String, sightIds: ArrayList<Int>): Route? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    parameters.add(description)
    var sightIdsString = ""
    sightIds.forEach {
      sightIdsString += "$it,"
    }
    if (sightIdsString.contains(",")) {
      sightIdsString = sightIdsString.removeRange(sightIdsString.length - 1, sightIdsString.length)
    }
    parameters.add(sightIdsString)
    if (executeSQL(INSERT_SQL, parameters)) {
      return queryRouteByName(name)
    }
    return null
  }

  override fun deleteRouteById(routeId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_ID, routeId)
  }

  override fun deleteAllRoutes(): Boolean {
    return deleteAll(TABLE_NAME)
  }

  override fun updateRoute(routeId: Int, name: String?, description: String?, sightIds: ArrayList<Int>?): Route? {
    if (StringUtils.isStringNull(name, description) && sightIds == null) {
      return null
    }
    var updateSQL = "UPDATE $TABLE_NAME SET "
    val parameters = ArrayList<Any>()
    if (name != null && name != "") {
      updateSQL += "$FIELD_NAME = ?,"
      parameters.add(name)
    }
    if (description != null && description != "") {
      updateSQL += "$FIELD_DESCRIPTION = ?,"
      parameters.add(description)
    }
    if (sightIds != null) {
      var sightIdsString = ""
      sightIds.forEach {
        sightIdsString += "$it,"
      }
      if (sightIdsString.contains(",")) {
        sightIdsString = sightIdsString.removeRange(sightIdsString.length - 1, sightIdsString.length)
      }
      updateSQL += "$FIELD_SIGHT_ID_CHAIN = ?,"
      parameters.add(sightIdsString)
    }
    if (updateSQL.contains(",")) {
      updateSQL = updateSQL.removeRange(updateSQL.length - 1, updateSQL.length)
      updateSQL += " WHERE $FIELD_ID = $routeId"
      if (executeSQL(updateSQL, parameters)) {
        return queryRouteById(routeId)
      }
    }
    return null
  }

  override fun queryRouteById(routeId: Int): Route? {
    val result = queryById(TABLE_NAME, FIELD_ID, routeId)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as Route
    }
    return null
  }

  override fun queryRouteByName(name: String): Route? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    val result = executeQuery(QUERY_ROUTE_BY_NAME, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as Route
    }
    return null
  }

  override fun queryAllRoutes(): ArrayList<Route>? {
    val result = queryAll(TABLE_NAME)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<Route>
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val routeList = ArrayList<Route>()
      val sightDao: SightDao = SightDaoImpl()
      while (resultSet.next()) {
        val route = Route()
        route.routeId = resultSet.getInt(FIELD_ID)
        route.name = resultSet.getString(FIELD_NAME)
        route.description = resultSet.getString(FIELD_DESCRIPTION)
        val sightIdChain = resultSet.getString(FIELD_SIGHT_ID_CHAIN)
        if (sightIdChain != null && sightIdChain != "") {
          val sightIdList = sightIdChain.split(",")
          val sightList = ArrayList<Sight>()
          sightIdList.forEach {
            val sight = sightDao.querySightById(it.toInt())
            if (sight != null) {
              sightList.add(sight)
            }
          }
          route.sightChain = sightList
        }
        route.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        route.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        routeList.add(route)
      }
      if (routeList.size > 0) {
        return routeList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}