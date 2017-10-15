package com.chengtao.wisdomgarden.db.impl

import com.chengtao.wisdomgarden.db.ConnectionPool
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
    try {
      return connectionPool!!.getConnection()!!.prepareStatement(sql).executeUpdate() > 0
    } catch (e: Exception) {
      printlnException("executeSQL", e)
    }
    return false
  }

  /**
   * 执行有参数的sql语句
   * @param sql : sql字符串
   * @param parameters : 参数列表
   */
  protected fun executeSQL(sql: String, parameters: ArrayList<Any>): Boolean {
    try {
      val ps: PreparedStatement = connectionPool!!.getConnection()!!.prepareStatement(sql)
      initPreparedStatementWithParameters(ps, parameters)
      return ps.executeUpdate() > 0
    } catch (e: Exception) {
      printlnException("executeSQL", e)
    }
    return false
  }

  /**
   * 执行查询
   * @param sql : sql字符串
   */
  protected fun executeQuery(sql: String): Any? {
    try {
      return convertResultSetToAny(
          connectionPool!!.getConnection()!!.prepareStatement(sql).executeQuery()
      )
    } catch (e: Exception) {
      printlnException("executeQuery", e)
    }
    return null
  }

  /**
   * 执行查询
   * @param sql : sql字符串
   * @param resultSetConvert : ResultSetConvert接口,用户特殊结果的处理
   */
  protected fun executeQuery(sql: String, resultSetConvert: ResultSetConvert): Any? {
    try {
      return resultSetConvert.convertResultSetToAny(
          connectionPool!!.getConnection()!!.prepareStatement(sql).executeQuery()
      )
    } catch (e: Exception) {
      printlnException("executeQuery", e)
    }
    return null
  }

  /**
   * 执行有参数的查询
   * @param sql : sql字符串
   * @param parameters : 参数列表
   */
  protected fun executeQuery(sql: String, parameters: ArrayList<Any>): Any? {
    try {
      val ps: PreparedStatement = connectionPool!!.getConnection()!!.prepareStatement(sql)
      initPreparedStatementWithParameters(ps, parameters)
      return convertResultSetToAny(ps.executeQuery())
    } catch (e: Exception) {
      printlnException("executeQuery", e)
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
    try {
      val ps: PreparedStatement = connectionPool!!.getConnection()!!.prepareStatement(sql)
      initPreparedStatementWithParameters(ps, parameters)
      return resultSetConvert.convertResultSetToAny(ps.executeQuery())
    } catch (e: Exception) {
      printlnException("executeQuery", e)
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
   * @param resultSetConvert  ResultSetConvert接口,用户特殊结果的处理
   */
  protected fun queryAll(tableName: String, resultSetConvert: ResultSetConvert): Any? {
    return executeQuery("SELECT * FROM $tableName", resultSetConvert)
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