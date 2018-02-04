spark streaming 整合 flume,kafka

python ---生成--- 日志 ---> flume收集 ---> kafka ---> spark streaming

1. 编写脚本 generate_log.py

2. 编写执行脚本 log_generate.sh
#!/bin/bash

# 注意 使用全路径 (crontab不使用全路径无效) which python
/usr/bin/python /home/ricky/data/generate_log.py

3. 编写定时任务
ricky@ricky:~/data$ crontab -l

*/1 * * * * /home/ricky/data/log_generate.sh # 每秒产生十条日志

4. 监控日志文件是否产生
cd /home/ricky/data/logs

tail -f access.log

5. 编写flume配置文件 vi streaming_project.conf

exec-memory-logger.sources = exec-source
exec-memory-logger.sinks = logger-sink
exec-memory-logger.channels = memory-channel

exec-memory-logger.sources.exec-source.type = exec
exec-memory-logger.sources.exec-source.command = tail -F /home/ricky/data/logs/access.log
exec-memory-logger.sources.exec-source.shell = /bin/sh -c

exec-memory-logger.channels.memory-channel.type = memory

exec-memory-logger.sinks.logger-sink.type = logger

exec-memory-logger.sources.exec-source.channels = memory-channel
exec-memory-logger.sinks.logger-sink.channel = memory-channel

6. 执行flume
flume-ng agent \
--name exec-memory-logger \
--conf $FLUME_HOME/conf \
--conf-file /home/ricky/app/flume-1.6.0-cdh5.7.0/conf/streaming_project.conf \
-Dflume.root.logger=INFO,console

7. 启动zk
cd $ZK_HOME
bin/zkServer.sh start

8. 启动Kafka
cd $KAFKA_HOME
bin/kafka-server-start.sh -daemon /home/ricky/app/kafka_2.11-0.9.0.0/config/server.properties

9. 修改flume配置文件使用flume sink数据到kafka
streaming_project2.conf
exec-memory-kafka.sources = exec-source
exec-memory-kafka.sinks = kafka-sink
exec-memory-kafka.channels = memory-channel

exec-memory-kafka.sources.exec-source.type = exec
exec-memory-kafka.sources.exec-source.command = tail -F /home/hadoop/data/logs/access.log
exec-memory-kafka.sources.exec-source.shell = /bin/sh -c

exec-memory-kafka.channels.memory-channel.type = memory

exec-memory-kafka.sinks.kafka-sink.type = org.apache.flume.sink.kafka.KafkaSink
exec-memory-kafka.sinks.kafka-sink.brokerList = ricky:9092
exec-memory-kafka.sinks.kafka-sink.topic = streamingtopic
exec-memory-kafka.sinks.kafka-sink.batchSize = 5
exec-memory-kafka.sinks.kafka-sink.requiredAcks = 1

exec-memory-kafka.sources.exec-source.channels = memory-channel
exec-memory-kafka.sinks.kafka-sink.channel = memory-channel

10. 启动消费者
kafka-console-consumer.sh --zookeeper ricky:2181 --topic streamingtopic

11. 启动flume
flume-ng agent \
--name exec-memory-kafka \
--conf $FLUME_HOME/conf \
--conf-file /home/ricky/app/flume-1.6.0-cdh5.7.0/conf/streaming_project2.conf \
-Dflume.root.logger=INFO,console
