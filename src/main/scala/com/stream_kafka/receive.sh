#!/bin/bash

spark-submit \
--master local[2] \
--class com.stream_kafka.KafkaRecevierWordCount \
--name KafkaRecevierWordCount \
--packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 \
/home/ricky/spark-jar/spark-learn-1.0-jar-with-dependencies.jar \
ricky:2181 test kafka_streaming_topic 1

spark-submit \
--class com.imooc.spark.KafkaReceiverWordCount \
--master local[2] \
--name KafkaReceiverWordCount \
--packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 \
/home/ricky/spark-jar/spark-learn-1.0.jar ricky:2181 test kafka_streaming_topic 1




spark-submit \
--class com.imooc.spark.KafkaDirectWordCount \
--master local[2] \
--name KafkaDirectWordCount \
--packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 \
/home/ricky/spark-jar/spark-learn-1.0.jar ricky:9092 kafka_streaming_topic


