package com.kafka;

/**
 * Kafka常用配置文件
 */
public class KafkaProperties {

    // 单节点 ZK="ricky:2181" 多节点 ZK="ricky1:2181,ricky2:2181"
    public static final String ZK = "127.0.0.1:2181";

    public static final String TOPIC = "wel_topic";

    // 单节点 BROKER_LIST="ricky:9092" 多节点 BROKER_LIST="ricky1:9092,ricky2:9092"
    public static final String BROKER_LIST = "127.0.0.1:9092";

    public static final String GROUP_ID = "test_group1";

}