package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.EcologyDao
import com.chengtao.wisdomgarden.entity.Ecology
import com.chengtao.wisdomgarden.utils.StringUtils
import java.sql.ResultSet

/**
 * Created by chengtao on 10/16/17.
 */
class EcologyDaoImpl : BaseDaoImpl(), EcologyDao {
  companion object {
    const val TABLE_NAME = "ecology"
    const val FIELD_SIGHT_ID = "sight_id"
    const val FIELD_TEMPERATURE = "temperature"
    const val FIELD_HUMIDITY = "humidity"
    const val FIELD_PM25 = "pm25"
    const val FIELD_WIND = "wind"
    const val FIELD_DRESSING = "dressing"
    const val FIELD_CAR_WASHING = "car_washing"
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME($FIELD_SIGHT_ID,$FIELD_TEMPERATURE,$FIELD_HUMIDITY," +
        "$FIELD_PM25,$FIELD_WIND,$FIELD_DRESSING,$FIELD_CAR_WASHING) VALUES (?,?,?,?,?,?,?)"
  }

  override fun createEcology(sightId: Int, temperature: Float, humidity: Float, pm25: Int, wind: Float,
                             dressing: String, carWashing: String): Ecology? {
    val parameters = ArrayList<Any>()
    parameters.add(sightId)
    parameters.add(temperature)
    parameters.add(humidity)
    parameters.add(pm25)
    parameters.add(wind)
    parameters.add(dressing)
    parameters.add(carWashing)
    if (executeSQL(INSERT_SQL, parameters)) {
      return queryEcologyBySightId(sightId)
    }
    return null
  }

  override fun deleteEcologyBySightId(sightId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_SIGHT_ID, sightId)
  }

  override fun updateEcology(sightId: Int, temperature: Float?, humidity: Float?, pm25: Int?,
                             wind: Float?, dressing: String?, carWashing: String?): Ecology? {
    if (temperature == null && humidity == null && pm25 == null && wind == null
        && StringUtils.isStringNull(dressing, carWashing)) {
      return null
    }
    var updateSQL = "UPDATE $TABLE_NAME SET "
    val parameters = ArrayList<Any>()
    if (temperature != null) {
      updateSQL += "$FIELD_TEMPERATURE = ?,"
      parameters.add(temperature)
    }
    if (humidity != null) {
      updateSQL += "$FIELD_HUMIDITY = ?,"
      parameters.add(humidity)
    }
    if (pm25 != null) {
      updateSQL += "$FIELD_PM25 = ?,"
      parameters.add(pm25)
    }
    if (wind != null) {
      updateSQL += "$FIELD_WIND = ?,"
      parameters.add(wind)
    }
    if (dressing != null && dressing != "") {
      updateSQL += "$FIELD_DRESSING = ?,"
      parameters.add(dressing)
    }
    if (carWashing != null && carWashing != "") {
      updateSQL += "$FIELD_CAR_WASHING = ?,"
      parameters.add(carWashing)
    }
    if (updateSQL.contains(",")) {
      updateSQL = updateSQL.removeRange(updateSQL.length - 1, updateSQL.length)
      updateSQL += " WHERE $FIELD_SIGHT_ID = $sightId"
      if (executeSQL(updateSQL, parameters)) {
        return queryEcologyBySightId(sightId)
      }
    }
    return null
  }

  override fun queryEcologyBySightId(sightId: Int): Ecology? {
    val result = queryById(TABLE_NAME, FIELD_SIGHT_ID, sightId)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Ecology
    }
    return null
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val ecologyList = ArrayList<Ecology>()
      while (resultSet.next()) {
        val ecology = Ecology()
        ecology.sightId = resultSet.getInt(FIELD_SIGHT_ID)
        ecology.temperature = resultSet.getFloat(FIELD_TEMPERATURE)
        ecology.humidity = resultSet.getFloat(FIELD_HUMIDITY)
        ecology.pm25 = resultSet.getInt(FIELD_PM25)
        ecology.wind = resultSet.getFloat(FIELD_WIND)
        ecology.dressing = resultSet.getString(FIELD_DRESSING)
        ecology.carWashing = resultSet.getString(FIELD_CAR_WASHING)
        ecology.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        ecology.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        ecologyList.add(ecology)
      }
      if (ecologyList.size > 0) {
        return ecologyList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}