AKKA (http://akka.io)

1. intro.html

<!-- akka 依赖 -->
<dependency>
  <groupId>com.typesafe.akka</groupId>
  <artifactId>akka-actor_2.11</artifactId>
  <version>2.3.14</version>
</dependency>

<dependency>
  <groupId>com.typesafe.akka</groupId>
  <artifactId>akka-remote_2.11</artifactId>
  <version>2.3.14</version>
</dependency>

1. preStart() 该方法在Actor对象构造方法执行后执行 整个Actor生命周期中仅执行一次

2. receive() 该方法在Actor的preStart方法的执行完成后执行 用于接收消息 会被反复执行

3. 编写 Master 类

4. 编写 Worker 类