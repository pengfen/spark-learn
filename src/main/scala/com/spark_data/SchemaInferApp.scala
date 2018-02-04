package com.spark_data

import org.apache.spark.sql.SparkSession

/**
  * Schema Infer
  */
object SchemaInferApp {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("SchemaInferApp").master("local[2]").getOrCreate()

    val df = spark.read.format("json").load("file:///home/ricky/data/json_schema_infer.json")

    df.printSchema()

    df.show()

    spark.stop()
  }

}