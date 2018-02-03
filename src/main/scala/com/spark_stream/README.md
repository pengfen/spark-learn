spark streaming 开发

1. 添加依赖
<spark.version>2.2.0</spark.version>

<!--  Spark Streaming 依赖 -->
<dependency>
  <groupId>org.apache.spark</groupId>
  <artifactId>spark-streaming_2.11</artifactId>
  <version>${spark.version}</version>
</dependency>

2. 编写 NetworkWordCount (spark streaming处理socket数据)

添加依赖
<dependency>
  <groupId>com.fasterxml.jackson.module</groupId>
  <artifactId>jackson-module-scala_2.11</artifactId>
  <version>2.6.5</version>
</dependency>
<dependency>
  <groupId>net.jpountz.lz4</groupId>
  <artifactId>lz4</artifactId>
  <version>1.3.0</version>
</dependency>

3. 编写 FileWordCount (spark streaming处理HDFS文件数据)