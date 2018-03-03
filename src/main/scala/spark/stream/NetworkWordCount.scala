package spark.stream;

/**
  * Spark Streaming处理Socket数据
  *
  * nc 的安装 sudo apt -y install netcat-traditional
  *
  * 测试： nc -lk 33333 输入字符
  *
  * 查看控制台
  *
  * 注意错误 Restarting receiver with delay 2000 ms: Error connecting to localhost:33333
  */
object NetworkWordCount {

  // socket ---local[1])--- Receiver ---> Memory
  // 对于需要Receiver来接收数据的处理 那么本地测试时你的local[?]一定要大于1的
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("NetworkWordCount").setMaster("local[2]")

    /**
      * 创建StreamingContext需要两个参数：SparkConf和batch interval
      */
    // 创建Spark Streaming Context，每隔5秒钟处理一批数据，那么这一秒收集的数据存放在哪，如何将收集的数据推送出去？
    // 是生产者主动推出去还是消费者每隔1秒钟来拉取一次数据
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val lines = ssc.socketTextStream("localhost", 33333)

    // flatMap是把将每一行使用空格做分解，那么words对应的数据结构是怎么样的？
    // words是个集合，每个集合元素依然是个集合，这个集合存放单词
    val result = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    result.print()

    // 启动计算作业
    ssc.start()
    // 等待结束，什么时候结束作业，即触发什么条件会让作业执行结束
    ssc.awaitTermination()
  }
}
