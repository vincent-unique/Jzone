package org.trump.vincent.rabbitmq.app.topic;

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
public class LogEmiter<M>{
    public static final String EXCHANGE_NAME_LOG = "topic_log";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void emit(M message,String routineKey){
        try{
            Channel channel = RabbitConnection.getConnection().createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME_LOG, BuiltinExchangeType.TOPIC);
            channel.basicPublish(EXCHANGE_NAME_LOG,routineKey,null,new Gson().toJson(message).getBytes());
            channel.close();
        }catch (IOException e){
            logger.error("Exception occurs in emitting log message.",e);
        }catch (TimeoutException e){
            logger.error("Exception occurs in emitting log message.",e);
        }
    }


    public static void main(String[] args) {
        MessageModel<String> errorLog =new MessageModel<>().setId(UUID.randomUUID().toString())
                .setRoutineKey(EXCHANGE_NAME_LOG+"_key");
        StringBuffer logContent = new StringBuffer("[ERROR] ").append("[Time:2017-12-06 15:30:35:345] ")
                .append("[00001087355] ").append("[module:cre_fulltextagent] ")
                .append("LogInfo:[THREAD_BEGIN][172.20.3.152][/fulltextagent/api/deleteBatch]");
        errorLog.setContent(logContent.toString());

        MessageModel<String> infoLog = new MessageModel<>().setId(UUID.randomUUID().toString())
                .setRoutineKey(EXCHANGE_NAME_LOG+"_key");
        logContent = new StringBuffer("[INFO] ").append("[Time:2017-12-06 15:30:35:345] ")
                .append("[00001087355] ").append("[module:cre_fulltextagent] ")
                .append("LogInfo:[THREAD_BEGIN][172.20.3.152][/fulltextagent/api/deleteBatch]");
        infoLog.setContent(logContent.toString());

        MessageModel<String> debugLog = new MessageModel<>().setId(UUID.randomUUID().toString())
                .setRoutineKey(EXCHANGE_NAME_LOG+"_key");
        logContent = new StringBuffer("[DEBUG] ").append("[Time:2017-12-06 15:30:35:345] ")
                .append("[00001087355] ").append("[module:cre_fulltextagent] ")
                .append("LogInfo:[THREAD_BEGIN][172.20.3.152][/fulltextagent/api/deleteBatch]");
        debugLog.setContent(logContent.toString());

        LogEmiter<String> logEmiter = new LogEmiter();

        logEmiter.emit(errorLog.getContent(),"log.topic.error");
        logEmiter.emit(infoLog.getContent(),"log.topic.info");
        logEmiter.emit(debugLog.getContent(),"log.topic.debug");
    }
}
