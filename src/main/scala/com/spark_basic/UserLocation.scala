package com.spark_basic

import org.apache.spark.{SparkConf, SparkContext}

object UserLocation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UserLocation").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.textFile("/home/ricky/data/log_1.log").map(x => {
      val arr = x.split(",")
      val mb = (arr(0), arr(2))
      val flag = arr(3)
      var time = arr(1).toLong
      if (flag == "1") time = -time
      (mb, time)
    })
    println(rdd1);

    val rdd2 = rdd1.reduceByKey(_+_)
    println(rdd2)

    val rdd3 = sc.textFile("/home/ricky/data/info_log.txt").map(x => {
      val arr = x.split(",")
      val bs = arr(0)
      (bs, (arr(1), arr(2)))
    })
    println(rdd3)

    val rdd4 = rdd2.map(t => (t._1._2, (t._1._1, t._2)))
    println(rdd4)

    val rdd5 = rdd4.join(rdd3)
    println(rdd5)

    val rdd6 = rdd2.map(t => (t._1._1, t._1._2, t._2)).groupBy(_._1).values.map(it => {
      it.toList.sortBy(_._3).reverse
    })

    //ArrayBuffer((16030401EAFB68F1E3CDF819735E1C66,((18611132889,97500),(116.296302,40.032296))), (16030401EAFB68F1E3CDF819735E1C66,((18688888888,87600),(116.296302,40.032296))))
    println(rdd5.collect.toBuffer)
  }
}
