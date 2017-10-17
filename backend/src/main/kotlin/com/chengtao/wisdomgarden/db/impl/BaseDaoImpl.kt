package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.ConnectionPool
import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * Created by chengtao on 10/13/17.
 */
@Suppress("unused", "MemberVisibilityCanPrivate")
abstract class BaseDaoImpl {
  companion object {
    //必有字段
    const val FIELD_CREATED_AT = "created_at"
    const val FIELD_UPDATED_AT = "updated_at"
  }

  //其他
  private var connectionPool: ConnectionPool? = null

  constructor() {
    connectionPool = ConnectionPool.instance
  }

  /**
   *　执行sql语句
   *  @param sql : sql字符串
   */
  protected fun executeSQL(sql: String): Boolean {
    val connection = connectionPool?.getConnection()
    try {
      if (connection != null) {
        val success = connection.prepareStatement(sql).executeUpdate() > 0
        connection.close()
        return success
      }
    } catch (e: Exception) {
      printlnException("executeSQL", e)
    } finally {
      close(connection)
    }
    return false
  }

  /**
   * 执行有参数的sql语句
   * @param sql : sql字符串
   * @param parameters : 参数列表
   */
  protected fun executeSQL(sql: String, parameters: ArrayList<Any>): Boolean {
    val connection = connectionPool?.getConnection()
    try {
      if (connection != null) {
        val ps: PreparedStatement = connection.prepareStatement(sql)
        initPreparedStatementWithParameters(ps, parameters)
        val success = ps.executeLargeUpdate() > 0
        connection.close()
        return success
      }
    } catch (e: Exception) {
      printlnException("executeSQL", e)
    } finally {
      close(connection)
    }
    return false
  }

  /**
   * 执行查询
   * @param sql : sql字符串
   */
  protected fun executeQuery(sql: String): Any? {
    val connection = connectionPool?.getConnection()
    try {
      if (connection != null) {
        val resultSet = connection.prepareStatement(sql).executeQuery()
        val result = convertResultSetToAny(resultSet)
        connection.close()
        return result
      }
    } catch (e: Exception) {
      printlnException("executeQuery", e)
    } finally {
      close(connection)
    }
    return null
  }

  /**
   * 执行查询
   * @param sql : sql字符串
   * @param resultSetConvert : ResultSetConvert接口,用户特殊结果的处理
   */
  protected fun executeQuery(sql: String, resultSetConvert: ResultSetConvert): Any? {
    val connection = connectionPool?.getConnection()
    try {
      if (connection != null) {
        val resultSet = connection.prepareStatement(sql).executeQuery()
        val result = resultSetConvert.convertResultSetToAny(resultSet)
        connection.close()
        return result
      }
    } catch (e: Exception) {
      printlnException("executeQuery", e)
    } finally {
      close(connection)
    }
    return null
  }

  /**
   * 执行有参数的查询
   * @param sql : sql字符串
   * @param parameters : 参数列表
   */
  protected fun executeQuery(sql: String, parameters: ArrayList<Any>): Any? {
    val connection = connectionPool?.getConnection()
    try {
      if (connection != null) {
        val ps: PreparedStatement = connection.prepareStatement(sql)
        initPreparedStatementWithParameters(ps, parameters)
        val result = convertResultSetToAny(ps.executeQuery())
        connection.close()
        return result
      }
    } catch (e: Exception) {
      printlnException("executeQuery", e)
    } finally {
      close(connection)
    }
    return null
  }

  /**
   * 执行有参数的查询
   * @param sql : sql字符串
   * @param parameters : 参数列表
   * @param resultSetConvert : ResultSetConvert接口,用户特殊结果的处理
   */
  protected fun executeQuery(sql: String, parameters: ArrayList<Any>, resultSetConvert: ResultSetConvert): Any? {
    val connection = connectionPool?.getConnection()
    try {
      if (connection != null) {
        val ps: PreparedStatement = connection.prepareStatement(sql)
        initPreparedStatementWithParameters(ps, parameters)
        val result = resultSetConvert.convertResultSetToAny(ps.executeQuery())
        connection.close()
        return result
      }
    } catch (e: Exception) {
      printlnException("executeQuery", e)
    } finally {
      close(connection)
    }
    return null
  }

  /**
   * 初始化PreparedStatement
   * @param ps PreparedStatement
   * @param parameters 参数列表
   */
  protected fun initPreparedStatementWithParameters(ps: PreparedStatement, parameters: ArrayList<Any>) {
    for ((index, value) in parameters.withIndex()) {
      when (value) {
        is String -> ps.setString(index + 1, value)
        is Int -> ps.setInt(index + 1, value)
        is Boolean -> ps.setBoolean(index + 1, value)
        is Float -> ps.setFloat(index + 1, value)
        is Double -> ps.setDouble(index + 1, value)
        is Date -> ps.setDate(index + 1, value)
      }
    }
  }

  protected fun printlnException(methodName: String, e: Exception) {
    println(methodName + ":" + e.message)
  }

  //默认提供的一些数据库相关操方法
  /**
   * 通过id删除记录
   * @param tableName 数据表名
   * @param idFieldName id的字段名
   * @param id id值
   */
  protected fun deleteById(tableName: String, idFieldName: String, id: Int): Boolean {
    return executeSQL("DELETE FROM $tableName WHERE $idFieldName = $id")
  }

  /**
   * 通过id删除记录
   * @param tableName 数据表名
   */
  protected fun deleteAll(tableName: String): Boolean {
    return executeSQL("TRUNCATE TABLE $tableName")
  }

  /**
   *通过id查询
   * @param tableName 数据表名
   * @param idFieldName id的字段名
   * @param id id值
   */
  protected fun queryById(tableName: String, idFieldName: String, id: Int): Any? {
    return executeQuery("SELECT * FROM $tableName WHERE $idFieldName = $id LIMIT 1")
  }

  /**
   *通过id查询
   * @param tableName 数据表名
   * @param idFieldName id的字段名
   * @param id id值
   * @param resultSetConvert  ResultSetConvert接口,用户特殊结果的处理
   */
  protected fun queryById(tableName: String, idFieldName: String, id: Int, resultSetConvert: ResultSetConvert): Any? {
    return executeQuery("SELECT * FROM $tableName WHERE $idFieldName = $id LIMIT 1", resultSetConvert)
  }

  /**
   * 查询所有
   * @param tableName 数据表名
   */
  protected fun queryAll(tableName: String): Any? {
    return executeQuery("SELECT * FROM $tableName")
  }

  /**
   * 查询所有
   * @param tableName 数据表名
   * @param resultSetConvert  ResultSetConvert接口,用户特殊结果的处理
   */
  protected fun queryAll(tableName: String, resultSetConvert: ResultSetConvert): Any? {
    return executeQuery("SELECT * FROM $tableName", resultSetConvert)
  }

  protected fun queryCount(tableName: String): Int {
    val result = executeQuery("SELECT count(*) as count FROM $tableName", object : ResultSetConvert {
      override fun convertResultSetToAny(resultSet: ResultSet): Any? {
        if (resultSet.next()) {
          return resultSet.getInt("count")
        }
        return 0
      }
    })
    if (result != null && result is Int) {
      return result
    }
    return 0
  }

  /**
   * 关闭数据库连接
   */
  private fun close(connection: Connection?) {
    if (connection != null && !connection.isClosed) {
      connection.close()
    }
  }

  //抽象方法
  /**
   * 将ResultSet转换成所需要的对象
   */
  abstract protected fun convertResultSetToAny(resultSet: ResultSet): Any?

  /**
   * ResultSet转换接口,用于特殊的装换
   */
  interface ResultSetConvert {
    fun convertResultSetToAny(resultSet: ResultSet): Any?
  }
}