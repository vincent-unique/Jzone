package org.trump.vincent.kafka.core;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerConnector;
import kafka.consumer.ZookeeperConsumerConnector;

import java.util.Properties;

/**
 * Created by Vincent on 2018/1/30 0030.
 */
public class ConsumerApplication {

    /**
     * Zookeeper connect-style ,look up the class [ ZKConfig$ ]
     * @return
     */
    public ConsumerConfig buidConsumerConfig(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
        properties.put("group.id","Log_Wokers");
        properties.put("zookeeper.session.timeout.ms","5000");
        properties.put("zookeeper.connection.timeout.ms","5000");
        properties.put("zookeeper.sync.time.ms","6000");
        properties.put("auto.commit.interval.ms","200");
        return  new ConsumerConfig(properties);
    }

    public void buildConsumerConnector(){
        ConsumerConnector consumerConnector = new ZookeeperConsumerConnector(buidConsumerConfig());
    }
}
