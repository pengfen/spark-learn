package com.kafka;

/**
 * Kafka Java API测试
 */
public class KafkaClientApp {

    public static void main(String[] args) {
        // 测试发消息
        new KafkaProducer(KafkaProperties.TOPIC).start();

        // 测试接收消息
        new KafkaConsumer(KafkaProperties.TOPIC).start();
    }
}
