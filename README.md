1. 创建项目
New ---> Project ---> Maven --- Project SDK 选择 java1.8(/home/ricky/app/jdk1.8.0-144) --->
选中 create from archetype ---> org.scala-tools.archetypes:scala-archtype-simple --->
GroupId:com
ArtifactId:spark
version:1.0 --->
Maven home durectory: /home/ricky/app/maven-3.3.9
Local repository: /home/ricky/repository ---> Project name：spark-learn

2. 添加依赖    
<scala.version>2.11.8</scala.version>

  <properties>    
    <scala.version>2.11.8</scala.version>    
  </properties>    

  <dependencies>    
    <dependency>    
      <groupId>org.scala-lang</groupId>    
      <artifactId>scala-library</artifactId>    
      <version>${scala.version}</version>    
    </dependency>    
  </dependencies>    

  import changes // 导入改变后pom.xml

3. 删除无关的文件    
删除 src/main/scala/com 目录下 App    
删除 src/main/scala/com 目录下 AppTest MySpec    

4. 使用上传项目至github    
github上创建项目 spark-learn    

cd /home/ricky/IdeaProjects/spark-learn
git init

vi .gitignore  // 设置忽略上传的文件    
.gitignore     // 忽略文件    
.idea/         // 忽略目录    
target/        // 忽略目录    

git add .    
git commit -a -m f    
git remote add origin https://github.com/pengfen/spark-learn.git    
git push -u origin master    

数据采集
Kettle
Logstash
Flume

数据传输
kafka

数据存储
HDFS
Hive
Hbase

数据处理
Presto
Phoenix
Kylin

搜索引擎
Elasticsearch

分布式集群
Zookeeper

分布式计算
Mapreduce
[Spark](https://github.com/pengfen/spark-learn/tree/master/src/main/scala/spark)

其它
[python](https://github.com/pengfen/spark-learn/tree/master/python) python 学习

[scala](https://github.com/pengfen/spark-learn/tree/master/src/main/scala/scala) scala 学习

