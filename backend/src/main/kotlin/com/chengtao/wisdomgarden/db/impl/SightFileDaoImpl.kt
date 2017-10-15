package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.SightFileDao
import com.chengtao.wisdomgarden.entity.FileCategory
import com.chengtao.wisdomgarden.entity.SightFile
import java.sql.ResultSet

@Suppress("MemberVisibilityCanPrivate")
/**
 * Created by chengtao on 10/15/17.
 */
class SightFileDaoImpl : BaseDaoImpl(), SightFileDao {
  companion object {
    const val TABLE_NAME = "sight_file"
    const val FIELD_SIGHT_ID = "sight_id"
    const val FIELD_NAME = "name"
    const val FIELD_CATEGORY = "category"
    const val FIELD_URL = "url"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME($FIELD_SIGHT_ID,$FIELD_NAME,$FIELD_CATEGORY,$FIELD_URL)" +
        " VALUES (?,?,?,?)"
    const val DELETE_SIGHT_FILE = "DELETE FROM $TABLE_NAME WHERE $FIELD_SIGHT_ID = ? AND $FIELD_URL = ?"
    const val DELETE_ALL_SIGHT_FILES = "DELETE FROM $TABLE_NAME WHERE $FIELD_SIGHT_ID = ?"
    const val QUERY_ALL_SIGHT_FILES = "SELECT * FROM $TABLE_NAME WHERE $FIELD_SIGHT_ID = ?"
  }

  override fun insertSightFile(sightId: Int, name: String, category: String, url: String): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(sightId)
    parameters.add(name)
    parameters.add(category)
    parameters.add(url)
    return executeSQL(INSERT_SQL, parameters)
  }

  override fun deleteSightFile(sightId: Int, url: String): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(sightId)
    parameters.add(url)
    return executeSQL(DELETE_SIGHT_FILE, parameters)
  }

  override fun deleteAllSightFiles(sightId: Int): Boolean {
    val parameters = ArrayList<Any>()
    parameters.add(sightId)
    return executeSQL(DELETE_ALL_SIGHT_FILES, parameters)
  }

  override fun querySightFiles(sightId: Int): ArrayList<SightFile>? {
    val parameters = ArrayList<Any>()
    parameters.add(sightId)
    val result = executeQuery(QUERY_ALL_SIGHT_FILES, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<SightFile>
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    val sightFileList = ArrayList<SightFile>()
    try {
      while (resultSet.next()) {
        val sightFile = SightFile()
        sightFile.sightId = resultSet.getInt(FIELD_SIGHT_ID)
        sightFile.name = resultSet.getString(FIELD_NAME)
        val category = resultSet.getString(FIELD_CATEGORY)
        if (category != null) {
          when (category) {
            FileCategory.IMAGE.value -> sightFile.category = FileCategory.IMAGE
            FileCategory.AUDIO.value -> sightFile.category = FileCategory.AUDIO
            FileCategory.VIDEO.value -> sightFile.category = FileCategory.VIDEO
          }
        }
        sightFile.url = resultSet.getString(FIELD_URL)
        sightFileList.add(sightFile)
      }
      resultSet.close()
      if (sightFileList.size > 0) {
        return sightFileList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}