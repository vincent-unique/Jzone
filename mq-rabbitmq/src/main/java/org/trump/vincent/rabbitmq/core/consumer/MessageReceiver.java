package org.trump.vincent.rabbitmq.core.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.rabbitmq.core.RabbitConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Vincent on 2017/12/5 0005.
 */
public class MessageReceiver {

    public static String QUEUE_NAME = "wiki_buf";

    /**
     * durable
     * determine that this Queue will be perssisted when The RabbitMQ goes down.
     */
    private boolean durable = true;

    private int prefetchNum = 1;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void process(){
        Channel channel = null;
        try {
            channel = RabbitConnection.getConnection().createChannel();
            /**
             * determine that get one message from the Channel once.
             */
            channel.basicQos(prefetchNum);

            channel.queueDeclare(  QUEUE_NAME,durable,false,false,null);
            Consumer consumer = new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException
                {
                    System.out.println("I get the message:"+new String(body,"UTF-8"));

                    logger.info("I get the message:",new String(body,"UTF-8"));
                }
            };
            /**
             * autoAck : determine whether acknowledge task completion
             */
            boolean autoAck = true;
            channel.basicConsume(QUEUE_NAME,autoAck,consumer);
        }catch (IOException e){
            logger.error("Exception occurs in sending message.",e);
        }
    }

    public static void main(String[] args) {
        new MessageReceiver().process();
    }
}
