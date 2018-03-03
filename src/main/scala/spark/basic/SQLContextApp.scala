package spark.basic

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * SQLContext的使用:
  * 注意：IDEA是在本地，而测试数据是在服务器上 ，能不能在本地进行开发测试的？
  *
  * 1. 添加依赖
  * 创建maven项目
File --- New --- Project --- 勾选create from archetype --- org.scala-tools.archetype:scala-archetype-simple
GroupId com.peng.wel
Artifactid sql

删除无用 main目录 ... App文件 test/scala目录下所有文件
添加相关依赖 pom.xml
<properties>
<scala.version>2.11.0</scala.version>
<spark.version>2.1.0</spark.version>
</properties>

<dependencies>
<!-- scala 依赖 -->
<dependency>
<groupId>org.scala-lang</groupId>
<artifactId>scala-library</artifactId>
<version>${scala.version}</version>
</dependency>

<!-- sparkSQL 依赖 -->
<dependency>
<groupId>org.apache.spark</groupId>
<artifactId>spark-sql_2.11</artifactId>
<version>${spark.version}</version>
</dependency>
</dependencies>
  *
  * 2. 编写代码
  *
  * 3. 本地测试  edit configurations ---> 左边选择Application ---> Name:sqlContextApp
  *
  * 4. 打包 mvn clean package -DskipTests
  *
  *  打包 Maven Projects ---> Lifecycle ---> package
  * 注意 打包前将setMaster()注释掉
  * 使用命令打包 cd IdeaProjects/spark-learn ---> mvn clean package -DskipTests
  * 打包后的文件 /home/ricky/IdeaProjects/spark-learn/target/spark-learn-1.0.jar
  *
  * 5. 上传jar包文件至服务器
  * ricky@ricky:~$ cd IdeaProjects/spark-learn/target/
  * ricky@ricky:~/IdeaProjects/spark-learn/target$ cp spark-learn-1.0.jar ~/spark-jar/
  *
  * 6. 服务器上运行
  *
  * 7. 编写脚本sql_context.sh
  */
object SQLContextApp {

  def main(args: Array[String]) {

    // {"name":"ricky"} json内容
    //val path = args(0)

    // val path = "file:///home/hadoop/system/resources/people.json";
    // val path = "/home/hadoop/system/resources/people.json"
    val path = "hdfs://ricky:9000/people.json"

    //1)创建相应的Context
    val sparkConf = new SparkConf()

    //在测试或者生产中，AppName和Master我们是通过脚本进行指定
    //sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    //2)相关的处理: json
    val people = sqlContext.read.format("json").load(path)
    people.printSchema()
    people.show()

    //3)关闭资源
    sc.stop()
  }

}