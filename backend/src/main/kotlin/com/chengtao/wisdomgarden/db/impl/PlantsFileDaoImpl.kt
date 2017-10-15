package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.PlantsFileDao
import com.chengtao.wisdomgarden.entity.FileCategory
import com.chengtao.wisdomgarden.entity.PlantsFile
import java.sql.ResultSet

/**
 * Created by chengtao on 10/15/17.
 */
class PlantsFileDaoImpl : BaseDaoImpl(), PlantsFileDao {
  companion object {
    const val TABLE_NAME = "plants_file"
    const val FIELD_PLANTS_ID = "plants_id"
    const val FIELD_NAME = "name"
    const val FIELD_CATEGORY = "category"
    const val FIELD_URL = "url"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME ($FIELD_PLANTS_ID,$FIELD_NAME,$FIELD_CATEGORY,$FIELD_URL)" +
        " VALUES (?,?,?,?)"
    const val DELETE_PLANTS_FILE_SQL = "DELETE FROM $TABLE_NAME WHERE $FIELD_PLANTS_ID = ? AND $FIELD_URL = ?"
    const val QUERY_PLANTS_ALL_FILES_BY_ID_SQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_PLANTS_ID = ?"
  }

  override fun insertPlantsFile(plantsId: Int, name: String, category: String, url: String): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(plantsId)
    parameters.add(name)
    parameters.add(category)
    parameters.add(url)
    return executeSQL(INSERT_SQL, parameters)
  }

  override fun deletePlantsFile(plantsId: Int, url: String): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(plantsId)
    parameters.add(url)
    return executeSQL(DELETE_PLANTS_FILE_SQL, parameters)
  }

  override fun deleteAllPlantsFiles(plantsId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_PLANTS_ID, plantsId)
  }

  override fun queryPlantsAllFiles(plantsId: Int): ArrayList<PlantsFile>? {
    val parameters = ArrayList<Any>()
    parameters.add(plantsId)
    val result = executeQuery(QUERY_PLANTS_ALL_FILES_BY_ID_SQL, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<PlantsFile>
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    val plantsFileList = ArrayList<PlantsFile>()
    try {
      while (resultSet.next()) {
        val plantsFile = PlantsFile()
        plantsFile.plantsId = resultSet.getInt(FIELD_PLANTS_ID)
        plantsFile.name = resultSet.getString(FIELD_NAME)
        val category = resultSet.getString(FIELD_CATEGORY)
        if (category != null) {
          when (category) {
            FileCategory.IMAGE.value -> plantsFile.category = FileCategory.IMAGE
            FileCategory.AUDIO.value -> plantsFile.category = FileCategory.AUDIO
            FileCategory.VIDEO.value -> plantsFile.category = FileCategory.VIDEO
          }
        }
        plantsFile.url = resultSet.getString(FIELD_URL)
        plantsFileList.add(plantsFile)
      }
      resultSet.close()
      if (plantsFileList.size > 0) {
        return plantsFileList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}