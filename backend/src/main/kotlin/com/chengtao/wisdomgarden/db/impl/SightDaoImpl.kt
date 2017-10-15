package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.utils.StringUtils
import java.sql.ResultSet

@Suppress("UNCHECKED_CAST")
/**
 * Created by chengtao on 10/15/17.
 */
class SightDaoImpl : BaseDaoImpl(), SightDao {
  companion object {
    const val TABLE_NAME = "sight"
    const val FIELD_ID = "id"
    const val FIELD_NAME = "name"
    const val FIELD_DESCRIPTION = "description"
    const val FIELD_LATITUDE = "latitude"
    const val FIELD_LONGITUDE = "longitude"
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
    //SQL语句
    const val INSERT_SIGHT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_NAME,$FIELD_DESCRIPTION," +
        "$FIELD_LATITUDE,$FIELD_LONGITUDE) VALUES (?,?,?,?)"
    const val QUERY_BY_NAME_SQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ? LIMIT 1"
  }

  override fun createSight(name: String, description: String, latitude: Float, longitude: Float): Sight? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    parameters.add(description)
    parameters.add(latitude)
    parameters.add(longitude)
    if (executeSQL(INSERT_SIGHT_SQL, parameters)) {
      return querySightByName(name)
    }
    return null
  }

  override fun deleteSightById(id: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_ID, id)
  }

  override fun updateSight(id: Int, name: String?, description: String?, latitude: Float?, longitude: Float?): Sight? {
    if (StringUtils.isStringNull(name) && StringUtils.isStringNull(description) && latitude == null && longitude == null) {
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
    if (latitude != null) {
      updateSQL += "$FIELD_LATITUDE = ?,"
      parameters.add(latitude)
    }
    if (longitude != null) {
      updateSQL += "$FIELD_LONGITUDE = ?,"
      parameters.add(longitude)
    }
    if (updateSQL.contains(",")) {
      updateSQL = updateSQL.removeRange(updateSQL.length - 1, updateSQL.length)
      updateSQL += " WHERE $FIELD_ID = $id"
      println(updateSQL)
      if (executeSQL(updateSQL, parameters)) {
        return querySightById(id)
      }
    }
    return null
  }

  override fun querySightById(id: Int): Sight? {
    val result: Any? = queryById(TABLE_NAME, FIELD_ID, id)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Sight?
    }
    return null
  }

  override fun querySightByName(name: String): Sight? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    val result = executeQuery(QUERY_BY_NAME_SQL, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Sight?
    }
    return null
  }

  override fun queryAllSight(): ArrayList<Sight>? {
    val result = queryAll(TABLE_NAME)
    if (result != null && result is ArrayList<*>) {
      return result as ArrayList<Sight>
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val resultList: ArrayList<Sight> = ArrayList()
      while (resultSet.next()) {
        val sight = Sight()
        sight.id = resultSet.getInt(FIELD_ID)
        sight.name = resultSet.getString(FIELD_NAME)
        sight.description = resultSet.getString(FIELD_DESCRIPTION)
        sight.latitude = resultSet.getFloat(FIELD_LATITUDE)
        sight.longitude = resultSet.getFloat(FIELD_LONGITUDE)
        sight.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        sight.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        if (sight.id != null) {
          sight.files = SightFileDaoImpl().querySightFiles(sight.id!!)
        }
        resultList.add(sight)
      }
      resultSet.close()
      if (resultList.size > 0) {
        return resultList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}