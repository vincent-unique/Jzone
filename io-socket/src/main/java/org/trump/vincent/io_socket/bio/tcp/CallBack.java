package org.trump.vincent.io_socket.bio.tcp;

import java.net.Socket;

public interface CallBack {
    void task(Socket socket);
}
