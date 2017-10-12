package com.chengtao.wisdomgarden.db

import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.Connection

/**
 * Created by chengtao on 10/12/17.
 */
class ConnectionPool {
  private constructor() {
    dataSource = ComboPooledDataSource()
    dataSource!!.jdbcUrl = MYSQL_JDBC_URL
    dataSource!!.driverClass = MYSQL_DRIVER_CLASS
    dataSource!!.user = MYSQL_USER
    dataSource!!.password = MYSQL_PASSWORD
    dataSource!!.acquireIncrement = MYSQL_ACQUIRE_INCREMENT
    dataSource!!.initialPoolSize = MYSQL_INITIAL_POOL_SIZE
    dataSource!!.minPoolSize = MYSQL_MIN_POOL_SIZE
    dataSource!!.maxPoolSize = MYSQL_MAX_POOL_SIZE
  }

  companion object {
    //mySQL手动配置
    const val MYSQL_JDBC_URL = "jdbc:mysql://127.0.0.1:3306/wisdom_garden?characterEncoding=utf8"
    const val MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver"
    const val MYSQL_USER = "root"
    const val MYSQL_PASSWORD = "root"
    const val MYSQL_ACQUIRE_INCREMENT = 3
    const val MYSQL_INITIAL_POOL_SIZE = 10
    const val MYSQL_MIN_POOL_SIZE = 10
    const val MYSQL_MAX_POOL_SIZE = 30
    //其他
    private var dataSource: ComboPooledDataSource? = null
    @Volatile
    var instance: ConnectionPool? = null
      get() {
        if (field == null) {
          synchronized(this) {
            field = ConnectionPool()
          }
        }
        return field
      }
  }

  fun getConnection(): Connection? {
    try {
      return dataSource!!.connection
    } catch (e: Exception) {
      println(e.message)
    }
    return null
  }
}