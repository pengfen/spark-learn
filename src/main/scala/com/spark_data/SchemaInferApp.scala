package com.spark_data

import org.apache.spark.sql.SparkSession

/**
  * Schema Infer
  *
  * 1. 编写代码
  *
  * hadoop fs -put schema.json /
  */
object SchemaInferApp {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("SchemaInferApp").master("local[2]").getOrCreate()

    // val in = "file:///home/ricky/data/spark/data/schema.json"
    // val in = "/home/ricky/data/spark/data/schema.json"
    val in = "hdfs://ricky:9000/schema.json"
    val df = spark.read.format("json").load(in)

    df.printSchema()
//    root
//    |-- gender: string (nullable = true)
//    |-- height: long (nullable = true)
//    |-- name: string (nullable = true)

    df.show()
//    +------+------+-------+
//    |gender|height|   name|
//    +------+------+-------+
//    |     M|   170|  ricky|
//      |     M|   175|caopeng|
//      |     F|   165|   peng|
//      +------+------+-------+

    spark.stop()
  }

}