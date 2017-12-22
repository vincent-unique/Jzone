package org.trump.vincent.io_socket.bio.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Map;

public class TcpClient {
    private Socket socket;

    private Logger logger = LoggerFactory.getLogger(getClass());

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
    public <T> void run(T request,Map response){
        String result = "";
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(request.toString());
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            CharBuffer buffer = CharBuffer.allocate(1024);
            while (reader.read(buffer) != -1) {
                result += buffer.toString();
                buffer.clear();
            }
            response.put("response",result);

            /**
             * Final task ,close the stream.
             */
            {
                writer.close();
                reader.close();

                /**
                 * socket closing is called by TCP client rather than TCP Server( SocketServer）
                 */
                socket.close();
            }
        }catch (IOException e){
            logger.error("Exception occurs in emitting the request or fetching the response.");
        }
    }
}
