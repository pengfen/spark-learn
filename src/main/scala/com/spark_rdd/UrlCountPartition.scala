package com.spark_rdd

import java.net.URL

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

object UrlCountPartition {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("UrlCountPartition").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // rdd1将数据切分 元组中放的是 (URL, 1)
    val rdd1 = sc.textFile("/home/ricky/data/itcast.log").map(line => {
      val f = line.split("\t")
      (f(1), 1)
    })
    val rdd2 = rdd1.reduceByKey(_ + _)

    val rdd3 = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, (url, t._2))
    })

    val ints = rdd3.map(_._1).distinct().collect()

    val hostParitioner = new HostParitioner(ints)

    //val rdd4 = rdd3.partitionBy(new HashPartitioner(ints.length))

    val rdd4 = rdd3.partitionBy(hostParitioner).mapPartitions(it => {
      it.toList.sortBy(_._2._2).reverse.take(2).iterator
    })

    //rdd4.saveAsTextFile("/home/ricky/data/out4")

    println(rdd4.collect().toBuffer) //ArrayBuffer((net.itcast.cn,(http://net.itcast.cn/net/course.shtml,521)), (net.itcast.cn,(http://net.itcast.cn/net/video.shtml,521)),
    sc.stop()
  }
}

/**
  * 决定了数据到哪个分区里面
  * @param ins
  */
class HostParitioner(ins: Array[String]) extends Partitioner {
  val parMap = new mutable.HashMap[String, Int]()
  var count = 0
  for (i <- ins) {
    parMap += (i -> count)
    count += 1
  }

  override def numPartitions: Int = ins.length

  override def getPartition(key: Any): Int = {
    parMap.getOrElse(key.toString, 0)
  }
}

