kafka API 开发

1. 添加依赖
<kafka.version>0.9.0.0</kafka.version>

    <!-- kafka 依赖 -->
    <dependency>
      <groupId>org.apache.kafka</groupId>
      <artifactId>kafka_2.11</artifactId>
      <version>${kafka.version}</version>
    </dependency>

Enable Auto-Import

2. 改变java目录属性
main 目录下创建 java目录
右上角结构或者(File ---> Project Structure) ---> Modules ---> java ---> Sources

3. 添加包 com.kafka

开发发消息
====================================================
4. 开发代码
编写 KafkaProperties.java
编写 KafkaProducer.java
编写 KafkaClientApp.java

5. 启动zk
cd $ZK_HOME
bin/zkServer.sh start

6. 启动kafka
cd $KAFKA_HOME
bin/kafka-server-start.sh -daemon /home/ricky/app/kafka_2.11-0.9.0.0/config/server.properties

7. 查看topic (看看所需topic是否创建)
bin/kafka-topics.sh --list --zookeeper ricky:2181

8. 创建topic
bin/kafka-topics.sh --create --zookeeper ricky:2181 --replication-factor 1 --partitions 1 --topic wel_topic

9. 启动消费
bin/kafka-console-consumer.sh --zookeeper ricky:2181 --topic wel_topic

10. 运行 KafkaClientApp

11. 查看控制台
Sent: message_1
Sent: message_2
Sent: message_3
Sent: message_4
Sent: message_5
Sent: message_6

12. 查看消费
message_1
message_2
message_3
message_4
message_5
message_6

开发消费消息
====================================================
13. 编写 KafkaConsumer

14. new KafkaConsumer(KafkaProperties.TOPIC).start();

15. 运行 KafkaClientApp

16. 查看控制台
Sent: message_2
rec: message_2
Sent: message_3
rec: message_3
Sent: message_4
rec: message_4
Sent: message_5
rec: message_5
rec: message_6