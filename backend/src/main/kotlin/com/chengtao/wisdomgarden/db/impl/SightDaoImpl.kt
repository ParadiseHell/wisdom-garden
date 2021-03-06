package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.EcologyDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.db.dao.SightFileDao
import com.chengtao.wisdomgarden.entity.FileCategory
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.entity.SightCateGory
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
    //SQL语句
    const val INSERT_SIGHT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_TYPE,$FIELD_NAME,$FIELD_DESCRIPTION," +
        "$FIELD_LATITUDE,$FIELD_LONGITUDE) VALUES (?,?,?,?,?)"
    const val QUERY_BY_NAME_SQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ? LIMIT 1"
    const val QUERY_ENTRANCE_SIGHT_ID = "SELECT $FIELD_ID FROM $TABLE_NAME WHERE $FIELD_TYPE = 1"
    const val QUERY_EXIT_SIGHT_ID = "SELECT $FIELD_ID FROM $TABLE_NAME WHERE $FIELD_TYPE = 2"
  }

  override fun createSight(type: Int, name: String, description: String, latitude: Float, longitude: Float): Sight? {
    val realType: Int
    if (type == SightCateGory.ENTRANCE.value) {//入口
      realType = SightCateGory.ENTRANCE.value
      if (existEntrance()) {//存在入口
        return null
      }
    } else if (type == SightCateGory.EXIT.value) {//出口
      realType = SightCateGory.EXIT.value
      if (existExit()) {//存在出口
        return null
      }
    } else {//其他景点
      realType = SightCateGory.OTHER.value
    }
    val parameters = ArrayList<Any>()
    parameters.add(realType)
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
    val result = deleteById(TABLE_NAME, FIELD_ID, id)
    if (id == queryEntranceSightId() || id == queryExitSightId()) {//出口或者入口
      //删除所有线路
      RouteDaoImpl().deleteAllRoutes()
    }
    //删除文件和景点对应关系
    SightFileDaoImpl().deleteAllSightFiles(id)
    //删除生态和景点对应关系
    EcologyDaoImpl().deleteEcologyBySightId(id)
    //删除植物和景点对应关系
    PlantsToSightDaoImpl().deletePlantsAllSights(id)
    return result
  }

  override fun updateSight(id: Int, category: Int?, name: String?, description: String?, latitude: Float?, longitude: Float?): Sight? {
    if (StringUtils.isStringNull(name) && StringUtils.isStringNull(description) && latitude == null && longitude == null) {
      return null
    }
    var updateSQL = "UPDATE $TABLE_NAME SET "
    val parameters = ArrayList<Any>()
    if (category != null) {
      updateSQL += "$FIELD_TYPE = ?,"
      parameters.add(category)
    }
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
    return queryCount(TABLE_NAME)
  }

  override fun queryEntranceSightId(): Int {
    val result = executeQuery(QUERY_ENTRANCE_SIGHT_ID, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        if (resultSet.next()) {
          return resultSet.getInt(FIELD_ID)
        }
        return -1
      }
    })
    if (result != null && result is Int) {
      return result
    }
    return -1
  }

  override fun queryExitSightId(): Int {
    val result = executeQuery(QUERY_EXIT_SIGHT_ID, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        if (resultSet.next()) {
          return resultSet.getInt(FIELD_ID)
        }
        return -1
      }
    })
    if (result != null && result is Int) {
      return result
    }
    return -1
  }

  override fun existEntrance(): Boolean {
    return queryEntranceSightId() > 0
  }

  override fun existExit(): Boolean {
    return queryExitSightId() > 0
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
          SightCateGory.ENTRANCE.value -> sight.type = SightCateGory.ENTRANCE
          SightCateGory.EXIT.value -> sight.type = SightCateGory.EXIT
        }
        if (sight.type == null) {
          sight.type = SightCateGory.OTHER
        }
        sight.name = resultSet.getString(FIELD_NAME)
        sight.description = resultSet.getString(FIELD_DESCRIPTION)
        sight.latitude = resultSet.getFloat(FIELD_LATITUDE)
        sight.longitude = resultSet.getFloat(FIELD_LONGITUDE)
        sight.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        sight.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        if (sight.id != null) {
          sightFileDao.querySightFiles(sight.id!!)?.forEach {
            if (it.category != null) {
              when (it.category) {
                FileCategory.IMAGE -> {
                  if (sight.images == null) {
                    sight.images = ArrayList()
                  }
                  sight.images!!.add(it)
                }
                FileCategory.VIDEO -> {
                  if (sight.videos == null) {
                    sight.videos = ArrayList()
                  }
                  sight.videos!!.add(it)
                }
                FileCategory.AUDIO -> {
                  if (sight.audios == null) {
                    sight.audios = ArrayList()
                  }
                  sight.audios!!.add(it)
                }
              }
            }
          }
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