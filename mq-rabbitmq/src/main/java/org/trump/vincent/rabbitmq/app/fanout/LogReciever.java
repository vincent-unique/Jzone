package org.trump.vincent.rabbitmq.app.fanout;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.rabbitmq.core.RabbitConnection;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class LogReciever {
    public static final String EXCHANGE_NAME_LOG = "logs";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void logProcess(){
        try {
            Channel channel = RabbitConnection.getConnection().createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME_LOG, BuiltinExchangeType.FANOUT);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName,EXCHANGE_NAME_LOG,"");

            Consumer consumer = new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException
                {
                    System.out.println("I ("+Thread.currentThread().getId()+")get the log message:"+new String(body,"UTF-8"));

                    logger.info("I get the log message:",new String(body,"UTF-8"));
                }
            };

            channel.basicConsume(queueName,true,consumer);
        }catch (IOException e){
            logger.error("Exception occurs in process the log massage.");
        }
    }


    public static void main(String[] args) {
        ExecutorService tPool = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    new LogReciever().logProcess();
                }
            };
            tPool.execute(run);
        }
    }
}
