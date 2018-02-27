package spark.spark_rdd;

object JdbcRDDDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("JdbcRDDDemo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val connection = () => {
      Class.forName("com.mysql.jdbc.Driver").newInstance()
      DriverManager.getConnection("jdbc:mysql://localhost:3306/resource", "root", "123456")
    }

    val jdbcRDD = new JdbcRDD(
      sc,
      connection,
      "select * from resource_auth where id >= ? and id <= ?",
      1, 4, 2,
      r => {
        val id = r.getInt(1)
        val code = r.getString(2)
        (id, code)
      }
    )

    val jrdd = jdbcRDD.collect()
    println(jdbcRDD.collect().toBuffer) // ArrayBuffer((1,权限管理), (2,权限列表), (3,管理员列表), (4,角色列表))

    sc.stop()
  }
}
