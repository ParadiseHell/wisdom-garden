package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.ConnectionPool
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * Created by chengtao on 10/12/17.
 */
fun main(args: Array<String>) {
  var connectionPool: ConnectionPool? = ConnectionPool.instance
  var connection: Connection? = connectionPool!!.getConnection()
  var ps: PreparedStatement = connection!!.prepareStatement("SELECT * FROM user")
  var rs: ResultSet = ps.executeQuery()
  val metaData = rs.metaData
  val columnsNumber = metaData.columnCount
  while (rs.next()) {
    for (i in 1..columnsNumber) {
      if (i > 1) print(",  ")
      val columnValue = rs.getString(i)
      print(columnValue + " " + metaData.getColumnName(i))
    }
    println("")
  }
}