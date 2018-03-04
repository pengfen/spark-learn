package spark.rdd

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}
;

/**
  * 根据指定的学科 取出点击量的前三
  *
  * 日期(年月日时分秒) 学科地址　　　　　　　
  * 20160321102628	http://java.itcast.cn/java/course/hadoop.shtml
  *
  * 数据源 /home/ricky/data/spark/rdd/subject.log
  */
object AdvUrlCount {

  def main(args: Array[String]): Unit = {
    // 从数据库中加载规则
    val arr = Array("java.itcast.cn", "php.itcast.cn", "net.itcast.cn")
    val conf = new SparkConf().setAppName("AdvUrlCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // rdd1 将数据切分 元组中放的是 (URL, 1)
    //val in = "file:///home/ricky/data/spark/rdd/subject.log"
    val in = "/home/ricky/data/spark/rdd/subject.log"
    //val in = "hdfs://ricky:9000/subject.log"
    val rdd1 = sc.textFile(in).map(line => {
      val f = line.split("\t")
      (f(1), 1)
    })
    println(rdd1)

    val rdd2 = rdd1.reduceByKey(_+_)
    println(rdd2)

    val rdd3 = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, url, t._2)
    })
    println(rdd3)
    println(rdd3.collect().toBuffer)

    val rddjava = rdd3.filter(_._1 == "java.itcast.cn")
    val sortdjava = rddjava.sortBy(_._3, false).take(3)
    val rddphp = rdd3.filter(_._1 == "php.itcast.cn")

    for (ins <- arr) {
      val rdd = rdd3.filter(_._1 == ins)
      val result = rdd.sortBy(_._3, false).take(3)

      // 通过 JDBC向数据库中存储数据
      // id 学院 URL 次数 访问日期
      println(result.toBuffer)
    }

    println(sortdjava.toBuffer)
    sc.stop()
  }
}
