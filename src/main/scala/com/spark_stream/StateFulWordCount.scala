package com.spark_stream

import com.spark_utils.LoggerLevels
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * 使用 Spark Streaming 完成有状态统计
  */
object StateFulWordCount {


  //Seq这个批次某个单词的次数
  //Option[Int]：以前的结果

  //分好组的数据
//  val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
//    //iter.flatMap(it=>Some(it._2.sum + it._3.getOrElse(0)).map(x=>(it._1,x)))
//    //iter.map{case(x,y,z)=>Some(y.sum + z.getOrElse(0)).map(m=>(x, m))}
//    //iter.map(t => (t._1, t._2.sum + t._3.getOrElse(0)))
//    iter.map{ case(word, current_count, history_count) => (word, current_count.sum + history_count.getOrElse(0)) }
//  }

  def main(args: Array[String]) {
    LoggerLevels.setStreamingLogLevels()
    //StreamingContext
    val conf = new SparkConf().setAppName("StateFulWordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)
    // 如果使用了stateful的算子 必须要设置 checkpoint
    // 在生产环境中 建议checkpoint设置到HDFS的某个文件夹中
    // ssc.checkpoint(".")
    //updateStateByKey必须设置setCheckpointDir
    sc.setCheckpointDir(".")
    val ssc = new StreamingContext(sc, Seconds(5))

    val ds = ssc.socketTextStream("localhost", 9999)

//    val result = ds.flatMap(_.split(" ")).map((_, 1))
//    val state = result.updateStateByKey[Int](updateFunction _)
//    state.print()
    //DStream是一个特殊的RDD
    //hello tom hello jerry
    //val result = ds.flatMap(_.split(" ")).map((_, 1)).updateStateByKey(updateFunc, new HashPartitioner(sc.defaultParallelism), true)
    //result.print()

    ssc.start()

    ssc.awaitTermination()

  }

  /**
    * 把当前的数据去更新已忣的或者是以前的数据
    * @param currentValues 当前的数据
    * @param preValues 以前的数据
    * @return
    */
//  def updateFunction(currentValues: Seq[Int], preValues: Option[Int]): Option[Int] = {
//    val current = currentValues.sum
//    val pre = preValues.getOrElse(0)
//    Some(current + pre)
//  }
}

