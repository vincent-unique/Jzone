package org.trump.vincent.io_socket.bio.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class TcpServer {

    private ServerSocket server;
    private Semaphore semaphore = new Semaphore(20);

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    public TcpServer(Integer port){
        try {
            server = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Blocking-wait-style for new socket connetcion
     * @param callBack
     * @throws IOException
     */
    @Deprecated
    public void syncRun(CallBack callBack)throws IOException{
        while (true){
            logger.info("New accept for new socket connection.");
            Socket socket = server.accept();
            logger.info("Main handler for socket request.");
            callBack.task(socket);
        }
    }

    /**
     * New-thread-style to handle new socket request
     * @param callBack
     * @throws IOException
     */
    public void asyncRun(CallBack callBack)throws IOException{
        while (true){
            Socket socket = server.accept();
            logger.info("Handler of new thread style for new socket connection.");
            new Thread(){
               public void run(){
                   try {
                       semaphore.acquire();
                       logger.info("Thread["+Thread.currentThread().getId()+"] is handling the socket request.");
                       callBack.task(socket);
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }finally {
                       semaphore.release();
                   }
               }
            }.start();
        }
    }
}
