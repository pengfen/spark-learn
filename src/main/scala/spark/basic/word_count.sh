#!/bin/bash

spark-submit \
--class spark.basic.WordCountApp \
--master local[2] \
--jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/wc.txt hdfs://ricky:9000/out