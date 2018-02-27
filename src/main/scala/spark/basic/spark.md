spark安装(单节点)

1. 下载
[百度云下载](https://pan.baidu.com/s/1dFYaCM1)

下载源码编译安装

2. 解压安装
tar -zxvf spark-2.2.0-bin-2.6.0-cdh5.7.0.tgz -C ~/app/

3. 配置环境变量
vi .bashrc
export SPARK_HOME=/home/ricky/app/spark-2.2.0-bin-2.6.0-cdh5.7.0
export PATH=$PATH:$SPARK_HOME/bin

4. 删除 *.cmd (方便快速定位)
cd $SPARK_HOME
rm -rf *.cmd

5. 启动 (local模式)
cd $SPARK_HOME
bin/spark-shell --master local[2]

参考 http://spark.apache.org/docs/latest/submitting-applications.html
http://localhost:4040/

6. 启动 (standalone模式 ---> 需要修改配置文件)
cd $SPARK_HOME/conf
cp spark-env.sh.template spark-env.sh
vi spark-env.sh
SPARK_MASTER_HOST=ricky
SPARK_WORKER_CORES=2
SPARK_WORKER_MEMORY=2g
SPARK_WORKER_INSTANCES=1

sbin/start-master.sh sbin/start-slaves.sh (启动主从节点 --- 集群 也可以使用sbin/start-all.sh)
http://localhost:8080/

bin/spark-shell --master spark://ricky:7077
bin/spark-shell --master spark://ricky:7077 --executor-cores 2 --executor-memory 2g

7. yarn模式 (生产上使用该模式 统一使用YARN进行整个集群作业的资源调度)
Client
Driver运行在Client端(提交Spark作业的机器)
Client会和请求到的Container进行通信来完成作业的调度和执行，Client是不能退出的
日志信息会在控制台输出：便于我们测试

Cluster
Driver运行在ApplicationMaster中
Client只要提交完作业之后就可以关掉，因为作业已经在YARN上运行了
日志是在终端看不到的，因为日志是在Driver上，只能通过yarn logs -applicationIdapplication_id

如果是yarn cluster模式的话，yarn-cluster

设置 HADOOP_CONF_DIR
1） export HADOOP_CONF_DIR=/home/ricky/app/hadoop-2.6.0-cdh5.7.0/etc/hadoop
2) $SPARK_HOME/conf/spark-env.sh

运行spark第一个程序 ---> 该算法是利用蒙特　卡罗算法求PI
spark-submit \
--class org.apache.spark.examples.SparkPi \
--master yarn-cluster \
--executor-memory 1G \
--num-executors 1 \
/home/ricky/app/spark-2.2.0-bin-2.6.0-cdh5.7.0/examples/jars/spark-examples_2.11-2.2.0.jar \
4

localhost:8088 ---> history ---> logs ---> stdout ---> 查看结果

8. spark的简单使用
scala> val file = spark.sparkContext.textFile("file:///home/ricky/data/spark/basic/wc.txt")
file: org.apache.spark.rdd.RDD[String] = file:///home/ricky/data/spark/basic/wc.txt MapPartitionsRDD[5] at textFile at <console>:23

scala> val wordCounts = file.flatMap(line => line.split(",")).map((word => (word, 1))).reduceByKey(_ + _)
wordCounts: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[8] at reduceByKey at <console>:25

scala> wordCounts.collect

localhost:8080 spark-shell state为waiting时，终端运行无用

scala> val file = spark.sparkContext.textFile("file:///home/ricky/data/spark/basic/wc.txt")
file: org.apache.spark.rdd.RDD[String] = file:///home/ricky/data/spark/basic/wc.txt MapPartitionsRDD[6] at textFile at <console>:23

scala> val wc = file.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
wc: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[9] at reduceByKey at <console>:25

scala> wc.collect
res2: Array[(String, Int)] = Array((hello,welcome,1), (hello,world,welcome,1))

scala> val wc = file.flatMap(_.split(",")).map((_,1)).reduceByKey(_+_)
wc: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[12] at reduceByKey at <console>:25

scala> wc.collect
res3: Array[(String, Int)] = Array((hello,2), (welcome,2), (world,1))
