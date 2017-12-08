package org.trump.vincent.rabbitmq.app.topic;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.rabbitmq.core.RabbitConnection;

import java.io.IOException;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class LogReciever {

    public static final String EXCHANGE_NAME_LOG = "topic_log";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void logProcess(String routinePattern){
        try{
            Channel channel = RabbitConnection.getConnection().createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME_LOG, BuiltinExchangeType.TOPIC);

            String queueName = channel.queueDeclare().getQueue();

            channel.queueBind(queueName,EXCHANGE_NAME_LOG,routinePattern);

            Consumer logConsumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException
                {
                    System.out.println("I ("+Thread.currentThread().getName()+") get the log message["+envelope.getRoutingKey()+
                            "] and will process it:["+new String(body,"utf-8"));
                    logger.info("I ("+Thread.currentThread().getName()+") get the log message and will process it.");
                }
            };

            channel.basicConsume(queueName,true,logConsumer);
        }catch (IOException e){
            logger.error("Exception occurs in processing the log message.",e);
        }
    }


    public static void main(String[] args) {
        /**
         * build routine pattern of fanout with topic
         * I.E， it will madtch all routines of the certain queue with "#"
         */
//        new LogReciever().logProcess("#");

        /**
         * build routine pattern of direct with topic
         */
//        new LogReciever().logProcess("log.topic.info");

        new LogReciever().logProcess("log.topic.*");
    }
}
