package org.trump.vincent.rabbitmq.core;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vincent on 2017/12/5 0005.
 */
public class RabbitConnection {
    public static ConnectionFactory connectionFactory = new ConnectionFactory();
    private static Connection connection;

    private static String host;
    private static Integer port;
    private static String username;
    private static String password;

    private static ReentrantLock lock = new ReentrantLock();
    private static Logger logger = LoggerFactory.getLogger(RabbitConnection.class);
    /**
     * Double Checked Locking of Singleton
     * Notice: The strategy of avoiding dcl drawback can be volatile OR classloader inialized LOCKER
     * @Link: http://cmsblogs.com/?p=2161
     * @return
     */
    public static Connection getConnection(){
        if(connection==null){
            lock.lock();
            if(connection==null){
                connectionFactory.setHost(host==null||host.isEmpty()?"localhost":host);
                if(port!=null){
                    connectionFactory.setPort(port);
                }
                if(!(username==null||username.isEmpty())){
                    connectionFactory.setUsername(username);
                }
                if(!(password==null||password.isEmpty())){
                    connectionFactory.setPassword(password);
                }
                try {
                    connection = connectionFactory.newConnection();
                }catch (IOException e){
                    logger.error("Exception occurs in build RabbitMQ connection",e);
                }catch (TimeoutException e){
                    logger.error("Exception occurs in build RabbitMQ connection",e);
                }finally {
                    lock.unlock();
                }
            }
        }
        return connection;
    }



}

