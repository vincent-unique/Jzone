package org.trump.vincent.kafka.constant;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class KafkaProperties {
    public static String zkConnetion = "127.0.0.1:12181,127.0.0.1:12182,127.0.0.1:12183";
    public static String groupId = "test-group";
    final static String topic = "topic1";
    final static String kafkaServerURL = "127.0.0.1";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "SimpleConsumerDemoClient";
}
