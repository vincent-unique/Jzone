package org.trump.vincent.rabbitmq.app.direct;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.rabbitmq.core.RabbitConnection;

import java.io.IOException;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class LogReciever {

    public static final String EXCHANGE_NAME_LOG = "log";

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Log messages Reciever and processor
     * @param keys
     */
    public void logProcess(String...keys){
        try {
            Channel channel = RabbitConnection.getConnection().createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME_LOG, BuiltinExchangeType.DIRECT);

            /**
             * Mapping multiple exchange routine to the same Queue
             */
            String queueName = channel.queueDeclare().getQueue();
            if(keys!=null&&keys.length>0) {
                for(String key :keys) {
                    channel.queueBind(queueName, EXCHANGE_NAME_LOG, key);
                }
            }

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

            channel.basicConsume(queueName,logConsumer);
        }catch (IOException e){
            logger.error("Exception occurs in recieving and processing the log messages.");
        }
    }


    public static void main(String[] args) {
        new Thread("error_log_message_reviever"){
            @Override
            public void run(){
                /**
                 * only one routine to the queue
                 */
                new LogReciever().logProcess("error");
            }
        }.start();

        new Thread("info_debug_log_message_reviever"){
            @Override
            public void run(){
                /**
                 * I.E. we will have tow routine (info and debug) to the certain Queue
                 */
                new LogReciever().logProcess("info","debug");
            }
        }.start();
    }
}
