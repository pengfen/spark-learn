package com.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("zk.connect", KafkaProperties.ZK);
        props.put("metadata.broker.list", KafkaProperties.BROKER_LIST);
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        for (int i = 1001; i <= 1100; i ++ )
            producer.send(new KeyedMessage<String, String>("apeng", "test" + i));
    }
}
