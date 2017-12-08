package org.trump.vincent.kafka.core;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class MessageProducer<M> {

    private Producer<String,M> producer;

    private Map producerConfigs;

    private String topicId;

    private Integer partionNum = 1;

    private Integer replicationNum = 1;


    public MessageProducer(Properties properties){
        this.producerConfigs = properties;
        this.producer = new KafkaProducer<String, M>(this.producerConfigs);
    }

    public MessageProducer(Properties properties,Integer partionNum,Integer replicationNum){
        this(properties);
        this.partionNum = partionNum;
        this.replicationNum = replicationNum;
    }

    public void publish(M message){
        if(producer==null){
            throw new NullPointerException("Null Kafka Message Producer.");
        }
        this.producer.send(new ProducerRecord<String, M>(this.topicId,message));
    }

    public void publish(M message, Callback callback){
        if(producer==null){
            throw new NullPointerException("Null Kafka Message Producer.");
        }
        this.producer.send(new ProducerRecord<String, M>(this.topicId,message),callback);
    }


    public Map getProducerConfigs() {
        return producerConfigs;
    }

    public MessageProducer setProducerConfigs(Map producerConfigs) {
        this.producerConfigs = producerConfigs;
        return this;
    }

    public String getTopicId() {
        return topicId;
    }

    public MessageProducer setTopicId(String topicId) {
        this.topicId = topicId;
        return this;
    }

    public Integer getPartionNum() {
        return partionNum;
    }

    public MessageProducer setPartionNum(Integer partionNum) {
        this.partionNum = partionNum;
        return this;
    }

    public Integer getReplicationNum() {
        return replicationNum;
    }

    public MessageProducer setReplicationNum(Integer replicationNum) {
        this.replicationNum = replicationNum;
        return this;
    }



    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        //The "all" setting we have specified will result in blocking on the full commit of the record, the slowest but most durable setting.
        //“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
        props.put("acks", "all");
        //如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
        props.put("retries", 0);

        //The producer maintains buffers of unsent records for each partition.
        props.put("batch.size", 16384);
        //默认立即发送，这里这是延时毫秒数
        props.put("linger.ms", 1);
        //生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
        props.put("buffer.memory", 33554432);
        //The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //创建kafka的生产者类
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        //生产者的主要方法
        // close();//Close this producer.
        //   close(long timeout, TimeUnit timeUnit); //This method waits up to timeout for the producer to complete the sending of all incomplete requests.
        //  flush() ;所有缓存记录被立刻发送
        for(int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("foo", i % 4, Integer.toString(i), Integer.toString(i)));
        }
        producer.close();
    }
}
