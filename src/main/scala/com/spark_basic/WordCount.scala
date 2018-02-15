package com.spark_basic

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 1. 编写代码
  *
  * 2. 本地测试 (传递参数) 运行代码
  *
  * 3. 打包 Maven Projects ---> Lifecycle ---> package
  * 打包后的文件 /home/ricky/IdeaProjects/spark-learn/target/sparktrain-1.0.jar
  *
  * 4. 上传jar包文件至服务器
  * ricky@ricky:~$ cd IdeaProjects/spark-learn/target/
  * ricky@ricky:~/IdeaProjects/spark-learn/target$ cp sparktrain-1.0.jar ~/spark-jar/
  *
  * 5. 服务器上运行
  * cd $SPARK_HOME
  * bin/spark-submit --class com.spark_basic.WordCount --executor-memory 512m --total-executor-cores 2 /home/ricky/spark-jar/sparktrain-1.0.jar
  * hdfs://ricky:9200/wc hdfs://ricky:9200/out
  *
  * 6. 查看是否成功 hadoop fs -ls /
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      // hdfs://ricky:9200/wc /home/ricky/data/out
      System.err.println("Usage: input_path output_path ")
      System.exit(1)
    }

    // spark 集群的入口
    val conf = new SparkConf().setAppName("WC")
      // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
      //.setMaster("local[2]")
    val sc = new SparkContext(conf)

    sc.textFile(args(0)).flatMap(_.split(" ")).map((_, 1))
      .reduceByKey(_+_).sortBy(_._2, false).saveAsTextFile(args(1))
    sc.stop()
  }
}
