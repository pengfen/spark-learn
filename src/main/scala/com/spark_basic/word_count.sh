#!/bin/bash

spark-submit \
--class com.spark_basic.WordCount \
--master local[2] \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/wc.txt hdfs://ricky:9000/out