package org.trump.vincent.rabbitmq.app.direct;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
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

    public static final String EXCHANGE_NAME_LOG = "log";

    private String exchangeRoutine;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void logEmit(M message){
        emit(message,this.exchangeRoutine==null?"":this.exchangeRoutine);
    }

    protected void emit(M message,String routine){

        try{
           Channel channel = RabbitConnection.getConnection().createChannel();
           channel.exchangeDeclare(EXCHANGE_NAME_LOG, BuiltinExchangeType.DIRECT);
           channel.basicPublish(EXCHANGE_NAME_LOG,routine, MessageProperties.PERSISTENT_TEXT_PLAIN,new Gson().toJson(message).getBytes());
           channel.close();
        }catch (IOException e){
            logger.error("Exception occurs in emitting the log messages.",e);
        }catch (TimeoutException e){
            logger.error("Exception occurs in emitting the log messages.",e);
        }
    }

    public LogEmiter(){

    }
    public String getExchangeRoutine() {
        return exchangeRoutine;
    }

    public LogEmiter setExchangeRoutine(String exchangeRoutine) {
        this.exchangeRoutine = exchangeRoutine;
        return this;
    }

    public LogEmiter(String exchangeRoutine) {
        this.exchangeRoutine = exchangeRoutine;
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

        /**
         * error log message emit
         */
        LogEmiter<String> errorEmiter = new LogEmiter<>("error");
        errorEmiter.logEmit(errorLog.getContent());

        /**
         * info level log message emit
         */
        LogEmiter<String> infoEmiter = new LogEmiter<>("info");
        infoEmiter.logEmit(infoLog.getContent());

        /**
         * debug level log message emit
         */
        LogEmiter<String> debugEmiter = new LogEmiter<>("debug");
        debugEmiter.logEmit(debugLog.getContent());
    }
}
