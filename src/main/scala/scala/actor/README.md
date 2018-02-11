intro.html 介绍

添加依赖
<!-- scala actor 依赖 -->
<dependency>
  <groupId>org.scala-lang</groupId>
  <artifactId>scala-actors</artifactId>
  <version>${scala.version}</version>
</dependency>

1. 编写 MyActor1

AKKA
1. preStart() 该方法在Actor对象构造方法执行后执行 整个Actor生命周期中仅执行一次

2. receive() 该方法在Actor的preStart方法的执行完成后执行 用于接收消息 会被反复执行

3. 编写 Master 类

4. 编写 Worker 类