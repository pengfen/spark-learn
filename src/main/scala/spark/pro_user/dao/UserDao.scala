package spark.pro_user.dao

import java.sql.{Connection, PreparedStatement}

import spark.pro_user.domain.User
import spark.utils.MySQLUtils

import scala.collection.mutable.ListBuffer

/**
  * 数据入库
  */
object UserDao {
  /**
    * 批量保存userTopN数据
    */
  def insertUserTonN(list: ListBuffer[User]): Unit = {

    var connection: Connection = null
    var pstmt: PreparedStatement = null

    try {
      connection = MySQLUtils.getConnection()

      connection.setAutoCommit(false) //设置手动提交

      // (mobile, bs, time, x, y)
      val sql = "insert into bs_user(mobile, bs, time, x, y) values (?,?,?,?,?) "
      pstmt = connection.prepareStatement(sql)

      for (ele <- list) {
        pstmt.setLong(1, ele.mobile)
        pstmt.setString(2, ele.bs)
        pstmt.setString(3, ele.time)
        pstmt.setDouble(4, ele.x)
        pstmt.setDouble(5, ele.y)

        pstmt.addBatch()
      }

      pstmt.executeBatch() // 执行批量处理
      connection.commit() //手工提交
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(connection, pstmt)
    }
  }
}
