package com.spark_sql

import org.apache.spark.sql.SparkSession

/**
  * SparkSession的使用
  */
object SparkSessionApp {

  def main(args: Array[String]) {

    if (args.length != 1) {
      // file:///home/ricky/data/people.json
      System.err.println("Usage: <SparkSessionApp> in_path")
      System.exit(1)
    }

    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()

    val people = spark.read.json("file:///home/ricky/data/people.json")
    people.show()

    spark.stop()
  }
}