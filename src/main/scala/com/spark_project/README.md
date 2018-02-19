日志分析项目

需求一 统计网站最受欢迎的课程/手记的Top N访问次数

1. 编写代码 SparkStatFormatJob

2. 编写代码 DateUtils

3. 编写代码 AccessConvertUtil

4. 编写代码 SparkStatCleanJob

5. 编写代码 IpUtils
ricky@ricky:~/software$ git clone https://github.com/wzhe06/ipdatabase.git
正克隆到 'ipdatabase'...
remote: Counting objects: 59, done.
remote: Total 59 (delta 0), reused 0 (delta 0), pack-reused 59
展开对象中: 100% (59/59), 完成.
ricky@ricky:~/software$ cd ipdatabase/
ricky@ricky:~/software/ipdatabase$ mvn clean package -DskipTests

6. 安装 ipdatabase
mvn install:install-file -Dfile=/home/ricky/software/ipdatabase/target/ipdatabase-1.0-SNAPSHOT.jar \
-DgroupId=com.ggstar \
-DartifactId=ipdatabase \
-Dversion=1.0 \
-Dpackaging=jar

7. 添加依赖
<dependency>
<groupId>com.ggstar</groupId>
<artifactId>ipdatabase</artifactId>
<version>1.0</version>
</dependency>

<dependency>
<groupId>org.apache.poi</groupId>
<artifactId>poi-ooxml</artifactId>
<version>3.14</version>
</dependency>

<dependency>
<groupId>org.apache.poi</groupId>
<artifactId>poi</artifactId>
<version>3.14</version>
</dependency>

按照需求完成统计信息并将统计结果入库
8. 编写代码 TopNStatJob

9. 编写代码 MySQLUtils

10. 编写代码 DayVideoAccessStat

11. 编写代码 StatDAO

需求二 按地市统计网站最受欢迎的Top N课程
12. 编写代码 DayCityVideoAccessStat

需求三 按流量统计网站最受欢迎的Top N课程
13. 编写代码 DayVideoTrafficsStat

改造在yarn上运行
14. 编写代码 SparkStatCleanJobYARN

15. 编写代码 TopNStatJobYARN

flow.html 项目运行

viedo.html java项目显示视图

flow2.html 项目升级版