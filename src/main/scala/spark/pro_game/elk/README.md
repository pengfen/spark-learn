1. logstash.html logstash 安装

2. 启动kafka

3. 编写配置文件 flow-es.conf (logstash ---> elasticsearch)

4. 编写配置言论 flow-kafka.conf (logstash ---> kafka)

/home/ricky/app/logstash-6.2.2/bin/logstash agent -f /home/ricky/app/logstash-6.2.2/conf/flow-kafka.conf