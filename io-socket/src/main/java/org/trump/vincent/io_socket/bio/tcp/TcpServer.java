package org.trump.vincent.io_socket.bio.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 *@Refernece http://www.jianshu.com/p/61ba3714e39a
 */
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
     * Full-Blocking-style for new socket connetcion
     * @param callBack
     * @throws IOException
     */
    @Deprecated
    public void fullBlockingRun(CallBack callBack)throws IOException{
        while (true){
            logger.info("New accept for new socket connection.");
            Socket socket = server.accept();
            logger.info("Main handler for socket request.");
            callBack.task(socket);
        }
    }

    /**
     * New-thread-style to handle new socket request
     * Ooptimize for Full-blocking-style and beyond thread expansion
     * @param callBack
     * @throws IOException
     */
    public void partBlockingRun (CallBack callBack)throws IOException{
        while (true){
            Socket socket = server.accept();
            logger.info("Handler of new thread style for new socket connection.");
            new Thread(){
               public void run(){
                   try {
                       semaphore.acquire();
                       logger.info("Thread["+Thread.currentThread().getId()+"] is handling the socket request.");

                       /**
                        * CallBack task contains some stream operations from socket.
                        * Notice close the streams inner task method
                        */
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

    /**
     * ThreadPool-style to handle IO accept and beyond threads expansion
     * @param callBack
     * @throws IOException
     */
    public void executorRun(CallBack callBack) throws IOException {
        ExecutorService tpool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()
                , 20, 200L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50, false));

        while (true) {
            Socket socket = server.accept();
            logger.info("Handler of new thread style for new socket connection.");
            tpool.execute(
                    new Runnable() {
                        public void run() {
                            try {
                                logger.info("Thread[" + Thread.currentThread().getId() + "] is handling the socket request.");

                                /**
                                 * CallBack task contains some stream operations from socket.
                                 * Notice close the streams inner task method
                                 */
                                callBack.task(socket);
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
    }


}
