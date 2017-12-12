package org.trump.vincent.io_socket.bio.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class TcpServer {

    private ServerSocket server;
    private Semaphore semaphore = new Semaphore(20);
    public TcpServer(Integer port){
        try {
            server = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(CallBack callBack)throws IOException{
        while (true){
            Socket socket = server.accept();

            new Thread(){
               public void run(){
                   try {
                       semaphore.acquire();
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
