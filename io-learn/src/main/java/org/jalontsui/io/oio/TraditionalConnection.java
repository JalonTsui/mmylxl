package org.jalontsui.io.oio;

import org.jalontsui.io.utils.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author JalonTsui
 * @Date 14:32 2025/5/31    
 **/

/**
 * 传统的创建一个server然后循环监听链接，并且处理链接
 * 1. 等待链接的时候会阻塞线程
 * 2. 一个请求如果没有处理完成就无法处理下一个链接
 */

public class TraditionalConnection implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.SOCKET_SERVER_PORT);
            while (!Thread.interrupted()) {
                Socket socket = serverSocket.accept();
                ConnectionPerThread.Handler handler = new ConnectionPerThread.Handler(socket);
                handler.run();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
