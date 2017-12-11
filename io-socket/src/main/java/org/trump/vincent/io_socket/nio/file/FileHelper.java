package org.trump.vincent.io_socket.nio.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * NIO — File
 * @Reference https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 */
public class FileHelper {

    /**
     * Read content from channel to buffer
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ByteBuffer read(String file)throws FileNotFoundException,IOException{
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer);
        return buffer;
    }

    /**
     * write data to channel
     * @param contents
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void write(byte[] contents,String file)throws FileNotFoundException,IOException{
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel fileChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(contents.length);
        buffer.put(contents);
        buffer.flip();
        fileChannel.write(buffer);
    }

    /**
     * Copy a file to another file
     * @param srcFile
     * @param targetFile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void fileCopy(String srcFile,String targetFile)throws FileNotFoundException,IOException{
        FileInputStream srcStream = new FileInputStream(srcFile);
        FileChannel srcChannel = srcStream.getChannel();

        FileOutputStream tgtStream = new FileOutputStream(targetFile);
        FileChannel tgtChannel = tgtStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            buffer.clear();
            if(srcChannel.read(buffer)==-1){
                break;
            }
            buffer.flip();
            tgtChannel.write(buffer);
        }
    }

}
