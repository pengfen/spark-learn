package com.spark_sql

import java.util.Properties

import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

// spark sql 可以通过jdbc从关系型数据库中读取数据的方式创建DataFrame 通过对DataFrame 一系列的计算后 还可以将数据再写回关系型数据库中
// val jdbcDF = sqlContext.read.format("jdbc").options(Map("url" -> "jdbc:mysql://localhost:3306/spark-learn", "driver" -> "com.mysql.jdbc.Driver", "dbtable" -> "person", "user" -> "root", "password" -> "123456")).load()
// jdbcDF.show()
//id  name   age
//3   kitty  30
//2   jerry  20

// 1. 编写代码

// 2. 创建表
//create table person (
//id int unsigned not null primary key auto_increment,
//name varchar(30) not null default "" comment "姓名",
//age int not null default 0 comment "年龄"
//) engine=innodb default charset=utf8;

// 3. 运行代码

// 4. 查看结果
//mysql> select * from person;
//+----+-------+-----+
//| id | name  | age |
//+----+-------+-----+
//|  1 | tom   |   5 |
//|  2 | jerry |   3 |
//|  3 | kitty |   6 |
//+----+-------+-----+
object JdbcRDD {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("JdbcRDD").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 通过并行化创建RDD
    val personRDD = sc.parallelize(Array("1 tom 5", "2 jerry 3", "3 kitty 6")).map(_.split(" "))

    // 通过 StructType 直接指定每个字段的schema
    val schema = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true)
      )
    )

    // 将 RDD 映射到 rowRDD
    val rowRDD = personRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).toInt))
    // 将schema 信息应用到rowRDD上
    val personDF = sqlContext.createDataFrame(rowRDD, schema)
    // 创建 Properties 存储数据库相关属性
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    // 将数据追加到数据库
    personDF.write.mode("append").jdbc("jdbc:mysql://localhost:3306/spark_learn", "spark_learn.person", prop)
    // 停止SparkContext
    sc.stop()
  }
}
