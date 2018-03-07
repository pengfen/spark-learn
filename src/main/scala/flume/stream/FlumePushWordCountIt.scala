package flume.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import spark.utils.LoggerLevels
;

object FlumePushWordCountIt {

  def main(args: Array[String]) {

    if (args.length != 2) {
      // ricky 8888
      System.err.println("Usage: FlumePushWordCountIt <hostname> <port>")
      System.exit(1)
    }

    val host = args(0)
    val port = args(1).toInt
    LoggerLevels.setStreamingLogLevels()
    val conf = new SparkConf().setAppName("FlumeWordCount")//.setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    //推送方式: flume向spark发送数据
    val flumeStream = FlumeUtils.createStream(ssc, host, port)
    //flume中的数据通过event.getBody()才能拿到真正的内容
    val words = flumeStream.flatMap(x => new String(x.event.getBody().array()).split(" ")).map((_, 1))

    val results = words.reduceByKey(_ + _)
    results.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
