package org.trump.vincent.mqrabbitmq.core.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent on 2017/12/5 0005.
 */
@Component
public class HelloSender {

    private static Logger logger = LoggerFactory.getLogger(HelloSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend("hello", message);
        logger.info(String.format("send message: %s", message));
    }

}