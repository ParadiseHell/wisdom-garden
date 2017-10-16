package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.PlantsToSightDao
import com.chengtao.wisdomgarden.db.dao.SightDao
import com.chengtao.wisdomgarden.entity.Sight
import java.sql.ResultSet

/**
 * Created by chengtao on 10/15/17.
 */
class PlantsToSightDaoImpl : BaseDaoImpl(), PlantsToSightDao {
  companion object {
    const val TABLE_NAME = "plants_to_sight"
    const val FIELD_PLANTS_ID = "plants_id"
    const val FIELD_SIGHT_ID = "sight_id"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_PLANTS_ID,$FIELD_SIGHT_ID)" +
        " VALUES (?,?)"
    const val DELETE_PLANTS_SIGHT_SQL = "DELETE FROM $TABLE_NAME " +
        "WHERE $FIELD_PLANTS_ID = ? AND $FIELD_SIGHT_ID = ?"
    const val QUERY_PLANTS_ALL_SIGHTS_SQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_PLANTS_ID = ?"
  }

  override fun insertPlantsSights(plantsId: Int, sightIds: ArrayList<Int>): Boolean {
    val parameters = ArrayList<Any>()
    sightIds.forEach {
      parameters.clear()
      parameters.add(plantsId)
      parameters.add(it)
      if (!executeSQL(INSERT_SQL, parameters)) {
        deleteAllPlantsSight(plantsId)
        return false
      }
    }
    return true
  }

  override fun deletePlantsSight(plantsId: Int, sightId: Int): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(plantsId)
    parameters.add(sightId)
    return executeSQL(DELETE_PLANTS_SIGHT_SQL, parameters)
  }

  override fun deleteAllPlantsSight(plantsId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_PLANTS_ID, plantsId)
  }

  override fun deletePlantsAllSights(sightId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_SIGHT_ID, sightId)
  }

  override fun updatePlantsSights(plantsId: Int, sightIds: ArrayList<Int>): Boolean {
    //先删除所有的植物与景点的对应关系
    deleteAllPlantsSight(plantsId)
    val parameters = ArrayList<Any>()
    sightIds.forEach {
      parameters.clear()
      parameters.add(plantsId)
      parameters.add(it)
      if (!executeSQL(INSERT_SQL, parameters)) {
        return false
      }
    }
    return true
  }

  override fun queryPlantsAllSights(plantsId: Int): ArrayList<Sight>? {
    val parameters = ArrayList<Any>()
    parameters.add(plantsId)
    val result = executeQuery(QUERY_PLANTS_ALL_SIGHTS_SQL, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<Sight>
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val sightList = ArrayList<Sight>()
      val sightDao: SightDao = SightDaoImpl()
      while (resultSet.next()) {
        val sightId = resultSet.getInt(FIELD_SIGHT_ID)
        val sight = sightDao.querySightById(sightId)
        if (sight != null) {
          sightList.add(sight)
        }
      }
      resultSet.close()
      if (sightList.size > 0) {
        return sightList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}