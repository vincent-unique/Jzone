package org.trump.vincent.io_socket.bio.tcp;

import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;

public class TcpClient {
    private Socket socket;

    public TcpClient(String host,Integer port){
        try {
            socket = new Socket(host, port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Please override the toString method for Generic type
     * @param request
     * @param response
     * @param <T>
     * @throws IOException
     */
    public <T> void run(T request,String response)throws IOException{
        String result = "";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write(request.toString());
        writer.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        CharBuffer buffer = CharBuffer.allocate(1024);
        while (reader.read(buffer)!=-1){
            result += buffer.toString();
            buffer.clear();
        }
        response = result;
    }
}
