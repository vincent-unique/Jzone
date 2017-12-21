package org.trump.vincent.io_socket.bio.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OptimizeTcpServer {

    private ServerSocket server;

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(20,50
            ,5, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    private Logger logger = LoggerFactory.getLogger(getClass());

    public OptimizeTcpServer(Integer port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            this.server = serverSocket;
        }catch (IOException e){
            logger.error("Exception occurs in OptimizeTcpServer Constuction.",e);
        }
    }

    /**
     * Optimization for socket server listener with ThreadPool
     * @param callBack
     * @throws IOException
     */
    public void run(CallBack callBack)throws IOException{
        Socket socket = this.server.accept();
        while (true){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    callBack.task(socket);
                }
            });
        }
    }
}
