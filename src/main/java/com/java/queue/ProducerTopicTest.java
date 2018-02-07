package com.java.queue;

import javax.jms.JMSException;

public class ProducerTopicTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws JMSException, Exception {
        ProducerTool producer = new ProducerTool();
        producer.produceMessage("Hello, world!");
        producer.close();
    }
}
