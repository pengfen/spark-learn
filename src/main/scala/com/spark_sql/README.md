1. 添加依赖
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

2. 编写代码 SQLContextApp

3. 编写代码 HiveContextApp
<!-- spark hive 依赖 -->
<dependency>
  <groupId>org.apache.spark</groupId>
  <artifactId>spark-hive_2.11</artifactId>
  <version>${spark.version}</version>
  <!--
  <scope>provided</scope>
  -->
</dependency>

4. 编写代码 SparkSessionApp

5. 编写代码 SparkSQLThriftServerApp

6. 编写代码 SQLDemo

7. 编写代码 InferringSchema

8. 编写代码 SpecifyingSchema

9. 编写代码 JdbcRDD
