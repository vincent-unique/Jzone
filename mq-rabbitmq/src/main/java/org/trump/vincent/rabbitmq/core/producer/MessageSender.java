package org.trump.vincent.rabbitmq.core.producer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.rabbitmq.core.RabbitConnection;
import org.trump.vincent.rabbitmq.model.MessageModel;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by Vincent on 2017/12/5 0005.
 */
public class MessageSender<M> {
    public static String QUEUE_NAME = "wiki_buf";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void send(M message){
        Channel channel = null;
        try {
            channel = RabbitConnection.getConnection().createChannel();
            /**
             * MessageProperties.PERSISTENT_TEXT_PLAIN
             * determine that the Messages in certain Queue will be perssisted when The RabbitMQ goes down.
             */
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, new Gson().toJson(message).getBytes("UTF-8"));

        }catch (IOException e){
            logger.error("Exception occurs in sending message.",e);
        }finally {
            if(channel!=null){
                try {
                    channel.close();
                }catch (TimeoutException e){
                    logger.error("Exception occurs in closing channel.",e);
                }catch (IOException e){
                    logger.error("Exception occurs in closing channel.",e);
                }
            }
        }
    }

    public static void main(String[] args) {
        MessageModel<String> messageModel = new MessageModel<>().setId(UUID.randomUUID().toString())
                .setRoutineKey(QUEUE_NAME).setContent(new StringBuffer("Wiki_index:\n").append("name:jockson\n").append("age:twenty"));

        new MessageSender<String>().send(messageModel.getContent());
    }
}
