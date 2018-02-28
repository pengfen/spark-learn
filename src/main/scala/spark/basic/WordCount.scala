package spark.basic

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 1. 编写代码
  *
  * 2. 本地测试 (传递参数) 运行代码
  * 输入参数使用 file  输出 hdfs://ricky:9000/out
  * 查看结果 hadoop fs -cat /out/part*
  *
  * 入参使用 /home/ricky/data/spark/basic/wc.txt
  * hadoop fs -rmr /out
  * 查看结果 hadoop fs -cat /out/part*
  *
  * 入参使用 hdfs://ricky:9000/wc.txt
  * hadoop fs -put wc.txt /
  * hadoop fs -rmr /out
  * 查看结果 hadoop fs -cat /out/part*
  *
  * 3. 打包 Maven Projects ---> Lifecycle ---> package
  * 注意 打包前将setMaster()注释掉
  * 使用命令打包 cd IdeaProjects/spark-learn ---> mvn clean package -DskipTests
  * 打包后的文件 /home/ricky/IdeaProjects/spark-learn/target/spark-learn-1.0.jar
  *
  * 4. 上传jar包文件至服务器
  * ricky@ricky:~$ cd IdeaProjects/spark-learn/target/
  * ricky@ricky:~/IdeaProjects/spark-learn/target$ cp spark-learn-1.0.jar ~/spark-jar/
  *
  * 5. 服务器上运行
  * cd $SPARK_HOME
  * hadoop fs -rmr /out
  * bin/spark-submit --class com.spark_basic.WordCount --executor-memory 512m --total-executor-cores 2 /home/ricky/spark-jar/spark-learn-1.0.jar
  * hdfs://ricky:9000/wc.txt hdfs://ricky:9000/out
  *
  * bin/spark-submit --class com.spark_basic.WordCount /home/ricky/spark-jar/spark-learn-1.0.jar
  *
  * 6. 查看结果 hadoop fs -cat /out/part*
  *
  * 7. 编写脚本 word_count.sh
  *
  */
object WordCount {
  def main(args: Array[String]): Unit = {
//    if (args.length != 2) {
//      // hdfs://ricky:9000/wc.txt hdfs://ricky:9000/out
//      System.err.println("Usage: input_path output_path ")
//      System.exit(1)
//    }
//
//    val Array(in, out) = args

    // 1. 使用file
    //val in = "file:///home/ricky/data/spark/basic/wc.txt"
    //val in = "/home/ricky/data/spark/basic/wc.txt"
    val in = "hdfs://ricky:9000/wc.txt"
    // 注意 1. cd $HADOOP_HOME ---> sbin/start-dfs.sh hdfs已启动
    // 2. out目录不存在 hadoop fs -rmr /out
    val out = "hdfs://ricky:9000/out"

    // spark 集群的入口
    val conf = new SparkConf()

    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    conf.setAppName("WC").setMaster("local[2]")

    val sc = new SparkContext(conf)

    // textFile 读取文件
    // flatMap(line => line.split(",")) ---> latMap(_.split(",")) 先map再压平
    // map((word => (word, 1))) ---> map((_, 1)) // 将单词和1构成元组
    // reduceByKey(_+_) 按照key进行reduce并将value累加
    // saveAsTextFile 将结果写到
    // sc.textFile(in).flatMap(_.split(",")).map((_, 1)).reduceByKey(_+_).sortBy(_._2, false).saveAsTextFile(out)

    sc.textFile(in).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).sortBy(_._2, false).saveAsTextFile(out)

// spark streaming 统计单词
//    val ssc = new StreamingContext(conf, Seconds(5));
//    val lines = ssc.socketTextStream("localhost", 33333)
//    val words = lines.flatMap(_.split(" "))
//    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
//    wordCounts.print()
//    ssc.start()
//    ssc.awaitTermination()

    sc.stop()
  }
}
