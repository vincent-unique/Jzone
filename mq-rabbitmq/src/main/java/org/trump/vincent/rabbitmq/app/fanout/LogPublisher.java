package org.trump.vincent.rabbitmq.app.fanout;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.rabbitmq.core.RabbitConnection;
import org.trump.vincent.rabbitmq.model.MessageModel;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by Vincent on 2017/12/6 0006.
 */
public class LogPublisher<M> {

    public static final String EXCHANGE_NAME_LOG = "logs";

    public static final String PUBLISH_STYLE = "fanout";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void publish(M logContent){
        try {
            Channel channel = RabbitConnection.getConnection().createChannel();
            /**
             * Declare an exchange with certain name and exchange type [ "fanout" ]
             */
            channel.exchangeDeclare(EXCHANGE_NAME_LOG, BuiltinExchangeType.FANOUT);

            channel.basicPublish(EXCHANGE_NAME_LOG,"",null,new Gson().toJson(logContent).getBytes());
            channel.close();

        }catch (IOException e){
            logger.error("Exception occurs in publishing log message.",e);
        }catch (TimeoutException e){
            logger.error("Exception occurs in publishing log message.",e);
        }
    }

    public static void main(String[] args) {
        MessageModel<String> logMessage = new MessageModel<>().setId(UUID.randomUUID().toString())
                .setRoutineKey(EXCHANGE_NAME_LOG+"_key");
        StringBuffer logContent = new StringBuffer("[INFO] ").append("[Time:2017-12-06 15:30:35:345] ")
                .append("[00001087355] ").append("[module:cre_fulltextagent] ")
                .append("LogInfo:[THREAD_BEGIN][172.20.3.152][/fulltextagent/api/deleteBatch]");
        logMessage.setContent(logContent.toString());

        new LogPublisher<String>().publish(logMessage.getContent());
    }
}
