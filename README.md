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
git add .
git commit -a -m f
git remote add origin https://github.com/pengfen/spark-learn.git
git push -u origin master
