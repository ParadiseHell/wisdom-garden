package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.PlantsDao
import com.chengtao.wisdomgarden.db.dao.PlantsFileDao
import com.chengtao.wisdomgarden.db.dao.PlantsToSightDao
import com.chengtao.wisdomgarden.entity.Plants
import com.chengtao.wisdomgarden.utils.StringUtils
import java.sql.ResultSet

/**
 * Created by chengtao on 10/15/17.
 */
class PlantsDaoImpl : BaseDaoImpl(), PlantsDao {
  companion object {
    const val TABLE_NAME = "plants"
    const val FIELD_ID = "id"
    const val FIELD_NAME = "name"
    const val FIELD_DESCRIPTION = "description"
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_NAME,$FIELD_DESCRIPTION)" +
        " VALUES (?,?)"
    const val QUERY_PLANTS_ID_BY_NAME = "SELECT $FIELD_ID FROM $TABLE_NAME WHERE $FIELD_NAME = ?"
    const val QUERY_PLANTS_BY_NAME = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ?"
  }

  override fun createPlants(name: String, description: String, sightIds: ArrayList<Int>): Plants? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    parameters.add(description)
    if (executeSQL(INSERT_SQL, parameters)) {
      val plantsId = queryPlantsIdByName(name)
      val plantsToSightDao: PlantsToSightDao = PlantsToSightDaoImpl()
      if (!plantsToSightDao.insertPlantsSights(plantsId, sightIds)) {
        deletePlantsById(plantsId)
        return null
      }
      return queryPlantsById(plantsId)
    }
    return null
  }

  override fun deletePlantsById(plantsId: Int): Boolean {
    val deleteSuccess = deleteById(TABLE_NAME, FIELD_ID, plantsId)
    if (deleteSuccess) {
      //删除该植物的所有文件
      PlantsFileDaoImpl().deleteAllPlantsFiles(plantsId)
      //删除该植物所对应的所有景点
      PlantsToSightDaoImpl().deleteAllPlantsSight(plantsId)
    }
    return deleteSuccess
  }

  override fun updatePlants(plantsId: Int, name: String?, description: String?, sightIds: ArrayList<Int>?): Plants? {
    if (StringUtils.isStringNull(name, description) && sightIds == null) {
      return null
    }
    var updateSQL = "UPDATE $TABLE_NAME SET "
    val parameters = ArrayList<Any>()
    var updateSuccess = false
    if (name != null && name != "") {
      updateSQL += "$FIELD_NAME = ?,"
      parameters.add(name)
    }
    if (description != null && description != "") {
      updateSQL += "$FIELD_DESCRIPTION = ?,"
      parameters.add(description)
    }
    if (updateSQL.contains(",")) {
      updateSQL = updateSQL.removeRange(updateSQL.length - 1, updateSQL.length)
      updateSQL += " WHERE $FIELD_ID = $plantsId"
      println(updateSQL)
      if (executeSQL(updateSQL, parameters)) {
        updateSuccess = true
      }
    }
    if (updateSuccess) {
      if (sightIds != null) {
        val plantsToSightDao: PlantsToSightDao = PlantsToSightDaoImpl()
        if (!plantsToSightDao.updatePlantsSights(plantsId, sightIds)) {
          updateSuccess = false
        }
      }
    }
    if (updateSuccess) {
      return queryPlantsById(plantsId)
    }
    return null
  }

  override fun queryPlantsById(plantsId: Int): Plants? {
    val result = queryById(TABLE_NAME, FIELD_ID, plantsId)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Plants
    }
    return null
  }

  override fun queryPlantsByName(name: String): Plants? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    val result = executeQuery(QUERY_PLANTS_BY_NAME, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Plants
    }
    return null
  }

  override fun queryAllPlants(): ArrayList<Plants>? {
    val result = queryAll(TABLE_NAME)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<Plants>
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    val plantsList = ArrayList<Plants>()
    val plantsFileDao: PlantsFileDao = PlantsFileDaoImpl()
    val plantsToSightDao: PlantsToSightDao = PlantsToSightDaoImpl()
    try {
      while (resultSet.next()) {
        val plants = Plants()
        plants.plantsId = resultSet.getInt(FIELD_ID)
        plants.name = resultSet.getString(FIELD_NAME)
        plants.description = resultSet.getString(FIELD_DESCRIPTION)
        plants.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        plants.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        if (plants.plantsId != null) {
          plants.files = plantsFileDao.queryPlantsAllFiles(plants.plantsId!!)
          plants.sights = plantsToSightDao.queryPlantsAllSights(plants.plantsId!!)
        }
        plantsList.add(plants)
      }
      resultSet.close()
      if (plantsList.size > 0) {
        return plantsList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

  /**
   * 通过植物的名称获取植物的id
   */
  private fun queryPlantsIdByName(name: String): Int {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    val result = executeQuery(QUERY_PLANTS_ID_BY_NAME, parameters, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        try {
          resultSet.next()
          val id = resultSet.getInt(FIELD_ID)
          resultSet.close()
          return id
        } catch (e: Exception) {
          printlnException("queryPlantsIdByName", e)
        } finally {
          resultSet.close()
        }
        return -1
      }
    })
    if (result != null && result is Int) {
      return result
    }
    return -1
  }
}