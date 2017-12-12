package org.trump.vincent.io_socket.bio.file;

import java.io.*;
import java.nio.CharBuffer;

public class FileHelper {

    public void write(String data, OutputStream outputStream)throws IOException{
        if(outputStream!=null){
            outputStream.write(data.getBytes());
        }
    }

    public byte[] read(InputStream inputStream)throws IOException{
        byte[] result = null;
        if(inputStream!=null){
            result = new byte[inputStream.available()];
            inputStream.read(result);
        }
        return result;
    }


    public void write(String data, Writer writer)throws IOException{
        if(writer!=null){
            writer.write(data);
        }
    }

    public String read(Reader reader)throws IOException{
        String result = "";
        CharBuffer buffer = CharBuffer.allocate(1024);
        if(reader!=null){
           while (reader.read(buffer)!=-1){
               result += buffer.toString();
               buffer.clear();
           }
        }
        return result;
    }
}
