package com.spark_project

import com.spark_project.utils.AccessConvertUtil
import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * 使用Spark完成我们的数据清洗操作
  */
object SparkStatCleanJob {

  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("SparkStatCleanJob")
      .config("spark.sql.parquet.compression.codec","gzip")
      .master("local[2]").getOrCreate()

    val accessRDD = spark.sparkContext.textFile("/home/ricky/data/access.log")

    //accessRDD.take(10).foreach(println)

    //RDD ==> DF
    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)),
      AccessConvertUtil.struct)

    //    accessDF.printSchema()
    //    accessDF.show(false)

    accessDF.coalesce(1).write.format("parquet").mode(SaveMode.Overwrite)
      .partitionBy("day").save("/home/ricky/data/clean")

    spark.stop
  }
}
