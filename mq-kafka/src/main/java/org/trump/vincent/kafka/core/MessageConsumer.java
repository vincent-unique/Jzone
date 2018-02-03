package org.trump.vincent.kafka.core;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class MessageConsumer<M>{
    private Map consumerConfigs;

    private KafkaConsumer<String,M> consumer;

    private String groupId;

    private Integer timeout = 500;

    public MessageConsumer(Map properties){
        this.consumerConfigs = properties;
        this.consumer = new KafkaConsumer<String, M>(consumerConfigs);
    }

    public MessageConsumer(Map properties,String groupId){
        this(properties);
        this.groupId = groupId;
    }

    public void subscribe(Collection<String> topics){
        if(this.consumer==null){
            throw new NullPointerException("Null Kafka Message Consumer.");
        }
        this.consumer.subscribe(topics);
    }

    public void onListener(Collection<String> topics,Woker<ConsumerRecord> woker){
        subscribe(topics);
        while (true){
           ConsumerRecords<String,M> consumerRecords = this.consumer.poll(this.timeout);
            if(consumerRecords!=null&& !consumerRecords.isEmpty()){
                for (ConsumerRecord<String,M> consumerRecord:consumerRecords){
                    woker.run(consumerRecord);
                }
            }
        }
    }

    public Integer getTimeout() {
        return timeout;
    }

    public MessageConsumer setTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public MessageConsumer setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public static void main(String[] args) {
        Properties props = new Properties();

//        props.put("bootstrap.servrs", "localhost:9092");
        //Connection config for kafka cluster [ brokers ]
        props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");


        System.out.println("this is the group part test 1");
        //消费者的组id
        props.put("group.id", "GroupA");//这里是GroupA或者GroupB

        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        //从poll(拉)的回话处理时长
        props.put("session.timeout.ms", "30000");
        //poll的数量限制
        // props.put("max.poll.records", "100");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //订阅主题列表topic
        consumer.subscribe(Arrays.asList("foo"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                //　正常这里应该使用线程池处理，不应该在这里处理
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()+"\n");

        }
    }
}
