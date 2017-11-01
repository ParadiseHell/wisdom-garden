package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.dao.ServiceDao
import com.chengtao.wisdomgarden.entity.Service
import com.chengtao.wisdomgarden.entity.ServiceNameAndCount
import java.sql.ResultSet

/**
 * Created by chengtao on 10/16/17.
 */
class ServiceDaoImpl : BaseDaoImpl(), ServiceDao {
  companion object {
    const val TABLE_NAME = "service"
    const val FIELD_ID = "id"
    const val FIELD_NAME = "name"
    const val FIELD_LATITUDE = "latitude"
    const val FIELD_LONGITUDE = "longitude"
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
    const val FIELD_COUNT = "count"
    //SQL语句
    const val INSERT_SQL = "INSERT INTO $TABLE_NAME($FIELD_NAME,$FIELD_LATITUDE,$FIELD_LONGITUDE)" +
        " VALUES (?,?,?)"
    const val QUERY_SERVICE_SQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ?" +
        " AND $FIELD_LATITUDE = ? AND $FIELD_LONGITUDE = ? LIMIT 1"
    const val QUERY_SERVICES_BY_NAME = "SELECT * FROM $TABLE_NAME WHERE $FIELD_NAME = ?"
    const val QUERY_SERVICES_COUNT = "SELECT count(*) as $FIELD_COUNT FROM " +
        "(SELECT $FIELD_NAME FROM $TABLE_NAME GROUP BY $FIELD_NAME) as new_$TABLE_NAME"
    const val QUERY_ALL_SERVICES_NAME_AND_COUNT_SQL = "SELECT $FIELD_NAME ,count(1) as $FIELD_COUNT " +
        "FROM $TABLE_NAME GROUP BY $FIELD_NAME"
  }

  override fun createService(name: String, latitude: Float, longitude: Float): Service? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    parameters.add(latitude)
    parameters.add(longitude)
    if (executeSQL(INSERT_SQL, parameters)) {
      return queryService(name, latitude, longitude)
    }
    return null
  }

  override fun deleteServiceById(serviceId: Int): Boolean {
    return deleteById(TABLE_NAME, FIELD_ID, serviceId)
  }

  override fun updateService(serviceId: Int, name: String?, latitude: Float?, longitude: Float?): Service? {
    if ((name == null || name == "") && latitude == null && longitude == null) {
      return null
    }
    if ((latitude != null && longitude == null) || (latitude == null && longitude != null)) {
      return null
    }
    var updateSQL = "UPDATE $TABLE_NAME SET "
    val parameters = ArrayList<Any>()
    if (name != null && name != "") {
      updateSQL += "$FIELD_NAME = ?,"
      parameters.add(name)
    }
    if (latitude != null && longitude != null) {
      updateSQL += "$FIELD_LATITUDE = ?,$FIELD_LONGITUDE = ?,"
      parameters.add(latitude)
      parameters.add(longitude)
    }
    if (updateSQL.contains(",")) {
      updateSQL = updateSQL.removeRange(updateSQL.length - 1, updateSQL.length)
      updateSQL += " WHERE $FIELD_ID = $serviceId"
      if (executeSQL(updateSQL, parameters)) {
        return queryByServiceId(serviceId)
      }
    }
    return null
  }

  override fun queryByServiceId(serviceId: Int): Service? {
    val result = queryById(TABLE_NAME, FIELD_ID, serviceId)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Service
    }
    return null
  }

  override fun queryServiceByLocation(latitude: Float, longitude: Float, range: Float): ArrayList<Service>? {
    val latitudeStart = latitude - range
    val latitudeEnd = latitude + range
    val longitudeStart = longitude - range
    val longitudeEnd = longitude + range
    val querySQL = "SELECT * FROM $TABLE_NAME WHERE $FIELD_LATITUDE BETWEEN $latitudeStart AND $latitudeEnd" +
        " AND $FIELD_LONGITUDE BETWEEN $longitudeStart AND $longitudeEnd"
    val result = executeQuery(querySQL)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<Service>
    }
    return null
  }

  override fun queryService(name: String, latitude: Float, longitude: Float): Service? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    parameters.add(latitude)
    parameters.add(longitude)
    val result = executeQuery(QUERY_SERVICE_SQL, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result[0] as Service
    }
    return null
  }

  override fun queryServicesByName(name: String): ArrayList<Service>? {
    val parameters = ArrayList<Any>()
    parameters.add(name)
    val result = executeQuery(QUERY_SERVICES_BY_NAME, parameters)
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<Service>
    }
    return null
  }

  override fun queryAllServiceNameAndCount(): ArrayList<ServiceNameAndCount>? {
    val result = executeQuery(QUERY_ALL_SERVICES_NAME_AND_COUNT_SQL, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        try {
          val serviceNameAndCountList = ArrayList<ServiceNameAndCount>()
          while (resultSet.next()) {
            val name = resultSet.getString(FIELD_NAME)
            val count = resultSet.getInt(FIELD_COUNT)
            if (name != null && name != "" && count > 0) {
              serviceNameAndCountList.add(ServiceNameAndCount(name, count))
            }
          }
          if (serviceNameAndCountList.size > 0) {
            return serviceNameAndCountList
          }
        } catch (e: Exception) {
          printlnException("queryAllServiceName", e)
        } finally {
          resultSet.next()
        }
        return null
      }
    })
    if (result != null && result is ArrayList<*> && result.size > 0) {
      return result as ArrayList<ServiceNameAndCount>
    }
    return null
  }

  override fun queryAllServicesCount(): Int {
    val result = executeQuery(QUERY_SERVICES_COUNT, object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        try {
          if (resultSet.next()) {
            return resultSet.getInt(FIELD_COUNT)
          }
        } catch (e: Exception) {

        } finally {
          resultSet.close()
        }
        return 0
      }
    })
    if (result != null && result is Int) {
      return result
    }
    return 0
  }

  override fun convertResultSetToAny(resultSet: ResultSet): Any? {
    try {
      val serviceList = ArrayList<Service>()
      while (resultSet.next()) {
        val service = Service()
        service.serviceId = resultSet.getInt(FIELD_ID)
        service.name = resultSet.getString(FIELD_NAME)
        service.latitude = resultSet.getFloat(FIELD_LATITUDE)
        service.longitude = resultSet.getFloat(FIELD_LONGITUDE)
        service.createdAt = resultSet.getDate(FIELD_CREATED_AT)
        service.updatedAt = resultSet.getDate(FIELD_UPDATED_AT)
        serviceList.add(service)
      }
      if (serviceList.size > 0) {
        return serviceList
      }
    } catch (e: Exception) {
      printlnException("convertResultSetToAny", e)
    } finally {
      resultSet.close()
    }
    return null
  }

}