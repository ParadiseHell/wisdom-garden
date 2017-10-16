package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.EcologyDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.dao.SightFileDao
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.entity.SightType
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
    const val FIELD_TYPE = "type"
    const val FIELD_NAME = "name"
    const val FIELD_DESCRIPTION = "description"
    const val FIELD_LATITUDE = "latitude"
    const val FIELD_LONGITUDE = "longitude"
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
    const val FIELD_COUNT = "count"
    //SQL语句
    const val INSERT_SIGHT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_TYPE,$FIELD_NAME,$FIELD_DESCRIPTION," +
        "$FIELD_LATITUDE,$FIELD_LONGITUDE) VALUES (?,?,?,?,?)"
    const val QUERY_BY_NAME_SQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ? LIMIT 1"
    const val QUERY_SIGHT_COUNT_SQL = "SELECT count(*) as $FIELD_COUNT FROM $TABLE_NAME"
    const val EXIST_ENTRANCE_SIGHT_SQL = "SELECT count(*) as $FIELD_COUNT FROM $TABLE_NAME WHERE $FIELD_TYPE = 1"
    const val EXIST_EXIT_SIGHT_SQL = "SELECT count(*) as $FIELD_COUNT FROM $TABLE_NAME WHERE $FIELD_TYPE = 2"
  }

  override fun createSight(type: Int, name: String, description: String, latitude: Float, longitude: Float): Sight? {
    val parameters = ArrayList<Any>()
    parameters.add(type)
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

  override fun querySightCount(): Int {
    val result = executeQuery(QUERY_SIGHT_COUNT_SQL, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        if (resultSet.next()) {
          return resultSet.getInt(FIELD_COUNT)
        }
        return 0
      }
    })
    if (result != null && result is Int) {
      return result
    }
    return 0
  }

  override fun existEntrance(): Boolean {
    val result = executeQuery(EXIST_ENTRANCE_SIGHT_SQL, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        if (resultSet.next()) {
          val count = resultSet.getInt(FIELD_COUNT)
          return count > 0
        }
        return false
      }
    })
    if (result != null && result is Boolean) {
      return result
    }
    return false
  }

  override fun existExit(): Boolean {
    val result = executeQuery(EXIST_EXIT_SIGHT_SQL, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        if (resultSet.next()) {
          val count = resultSet.getInt(FIELD_COUNT)
          return count > 0
        }
        return false
      }
    })
    if (result != null && result is Boolean) {
      return result
    }
    return false
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val resultList: ArrayList<Sight> = ArrayList()
      val sightFileDao: SightFileDao = SightFileDaoImpl()
      val ecologyDao: EcologyDao = EcologyDaoImpl()
      while (resultSet.next()) {
        val sight = Sight()
        sight.id = resultSet.getInt(FIELD_ID)
        val type = resultSet.getInt(FIELD_TYPE)
        when (type) {
          SightType.OTHER.value -> sight.type = SightType.OTHER
          SightType.ENTRANCE.value -> sight.type = SightType.ENTRANCE
          SightType.EXIT.value -> sight.type = SightType.EXIT
        }
        sight.name = resultSet.getString(FIELD_NAME)
        sight.description = resultSet.getString(FIELD_DESCRIPTION)
        sight.latitude = resultSet.getFloat(FIELD_LATITUDE)
        sight.longitude = resultSet.getFloat(FIELD_LONGITUDE)
        sight.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        sight.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        if (sight.id != null) {
          sight.files = sightFileDao.querySightFiles(sight.id!!)
          sight.ecology = ecologyDao.queryEcologyBySightId(sight.id!!)
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