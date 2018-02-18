package com.spark_sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 1. 编写代码
  *
  * 2. 编写 person.txt
  * 1,caopeng1,18
  * 2,caopeng2,20
  * 3,caopeng3,30
  *
  * 3. 上传 person.txt 至 hdfs
  * hadoop fs -put person.txt /
  * hadoop fs -ls /
  *
  */
object SQLDemo {

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      // hdfs://ricky:9200/person.txt
      System.err.println("Usage: <SQLDemo> in_path ")
      System.exit(1)
    }

    val conf = new SparkConf().setAppName("SQLDemo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    System.setProperty("user.name", "ricky")
    val personRdd = sc.textFile("hdfs://ricky:9200/person.txt").map(line => {
      val fields = line.split(",")
      //Person(fields(0).toLong, fields(1), fields(2).toInt)
    })

//    import sqlContext.implicits._
//    val personDf = personRdd.toDF()
//
//    personDf.registerTempTable("t_person")
//
//    sqlContext.sql("select * from t_person where age >= 20 order by age desc limit 2").show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  3|caopeng3| 30|
//      |  2|caopeng2| 20|
//      +---+--------+---+

    sc.stop()
  }
}

//case class Person(id: Long, name: String, age: Int)

// 1. 创建文件 person.txt(三列 id,name,age) 然后上传到hdfs
// hadoop hdfs -put person.txt /

//cd $SPARK_HOME
//bin/spark-shell --master local[2]
//
//scala> sc
//res40: org.apache.spark.SparkContext = org.apache.spark.SparkContext@6892d403
//
//scala> val sqlContext = new org.apache.spark.sql.SQLContext(sc)
//warning: there was one deprecation warning; re-run with -deprecation for details
//sqlContext: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@4d45bb93
//
//scala> sqlContext
//res42: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@4d45bb93
// 读取数据 将第一行的数据使用列分隔符分割
//scala> val rdd = sc.textFile("hdfs://ricky:9200/person.txt").map(_.split(","))
//rdd: org.apache.spark.rdd.RDD[Array[String]] = MapPartitionsRDD[106] at map at <console>:24
// 定义case class (相当于表的schema)
//scala> case class Person(id: Long, name: String, age: Int)
//defined class Person
// 将 rdd 和 case class 关联
//scala> val personRDD = rdd.map(x => Person(x(0).toLong, x(1), x(2).toInt))
//personRDD: org.apache.spark.rdd.RDD[Person] = MapPartitionsRDD[107] at map at <console>:28
// 将 rdd 转换成 dataframe
//scala> personRDD.toDF
//res43: org.apache.spark.sql.DataFrame = [id: bigint, name: string ... 1 more field]
//
//scala> val df = personRDD.toDF
//df: org.apache.spark.sql.DataFrame = [id: bigint, name: string ... 1 more field]
// 对 dataframe 进行处理
//scala> df.show() // 查看dataframe 部分列中的内容
//+---+--------+---+
//| id|    name|age|
//+---+--------+---+
//|  1|caopeng1| 18|
//|  2|caopeng2| 20|
//|  3|caopeng3| 30|
//+---+--------+---+

// 查看DataFrame部分列中的内容
//df.select(df.col("name")).show
//df.select(col("name"),col("age")).show
//df.select("name").show
//scala> df.select(df.col("name")).show
//+--------+
//|    name|
//+--------+
//|caopeng1|
//|caopeng2|
//|caopeng3|
//+--------+
//
//
//scala> df.select(col("name"), col("age")).show
//+--------+---+
//|    name|age|
//+--------+---+
//|caopeng1| 18|
//|caopeng2| 20|
//|caopeng3| 30|
//+--------+---+
//
//
//scala> df.select("name").show
//+--------+
//|    name|
//+--------+
//|caopeng1|
//|caopeng2|
//|caopeng3|
//+--------+

// 打印 DataFrame的Schema信息
//df.printSchema
//scala> df.printSchema
//root
//|-- id: long (nullable = false)
//|-- name: string (nullable = true)
//|-- age: integer (nullable = false)
//
//// 查询所有的name和age 并将age+1
//df.select(col("id"), col("name"), col("age") + 1).show
//df.select(df("id"), df("name"), df("age") + 1).show
//scala> df.select(col("id"), col("name"), col("age") + 1).show
//+---+--------+---------+
//| id|    name|(age + 1)|
//+---+--------+---------+
//|  1|caopeng1|       19|
//|  2|caopeng2|       21|
//|  3|caopeng3|       31|
//+---+--------+---------+
//
//
//scala> df.select(df("id"), df("name"), df("age") + 1).show
//+---+--------+---------+
//| id|    name|(age + 1)|
//+---+--------+---------+
//|  1|caopeng1|       19|
//|  2|caopeng2|       21|
//|  3|caopeng3|       31|
//+---+--------+---------+

// 过滤age大于等于18的
//df.filter(col("age") >= 18).show
//scala> df.filter(col("age") >= 18).show
//+---+--------+---+
//| id|    name|age|
//+---+--------+---+
//|  1|caopeng1| 18|
//|  2|caopeng2| 20|
//|  3|caopeng3| 30|
//+---+--------+---+
//
//// 按年龄进行地分组并统计相同年龄的人数
//df.groupBy("age").count().show()
//scala> df.groupBy("age").count().show()
//+---+-----+
//|age|count|
//+---+-----+
//| 20|    1|
//| 30|    1|
//| 18|    1|
//+---+-----+

//sql风格语法
//如果想使用sql风格的语法 需要将DataFrame注册成表
//df.registerTempTable("t_person")
//scala> df.registerTempTable("t_person")
//warning: there was one deprecation warning; re-run with -deprecation for details
//
//// 查询年龄最大的前两名
//sqlContext.sql("select * from t_person order by age desc limit 2").show
//scala> sqlContext.sql("select * from t_person order by age desc limit 2").show
//+---+--------+---+
//| id|    name|age|
//+---+--------+---+
//|  3|caopeng3| 30|
//|  2|caopeng2| 20|
//+---+--------+---+
//
//// 显示表的Schema信息
//sqlContext.sql("desc t_person").show
//scala> sqlContext.sql("desc t_person").show
//+--------+---------+-------+
//|col_name|data_type|comment|
//+--------+---------+-------+
//|      id|   bigint|   null|
//|    name|   string|   null|
//|     age|      int|   null|
//+--------+---------+-------+

