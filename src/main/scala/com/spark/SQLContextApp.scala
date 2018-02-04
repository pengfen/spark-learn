package com.spark

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

import org.apache.spark.SparkConf

/**
  * SQLContext的使用:
  * 注意：IDEA是在本地，而测试数据是在服务器上 ，能不能在本地进行开发测试的？
  *
  * Program argumets: file:///home/ricky/data/people.json
[hadoop@base data]$ cat people.json
{"name":"ricky"}
{"name":"caopeng"}
{"name":"peng"}

  * mvn clean package -DskipTests
  */
object SQLContextApp {

  def main(args: Array[String]) {

    val path = args(0)

    //1)创建相应的Context
    val sparkConf = new SparkConf()

    //在测试或者生产中，AppName和Master我们是通过脚本进行指定
    //sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    //2)相关的处理: json
    val people = sqlContext.read.format("json").load(path)
    people.printSchema()
    people.show()

    //3)关闭资源
    sc.stop()
  }

}
