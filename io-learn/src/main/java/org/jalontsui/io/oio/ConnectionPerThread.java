package org.jalontsui.io.oio;

import org.jalontsui.io.utils.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author JalonTsui
 * @Date 14:34 2025/5/31
 **/

/**
 * 一个链接开启单独一个线程去处理
 * 1. 在系统中，线程是比较昂贵的资源，如果线程数量太多，系统将会无法承受，所以在高并发场景下OIO(old input-output)是无法接受的
 *
 * problem
 * 1. 怎么去减少线程的数量
 */

public class ConnectionPerThread implements Runnable {

    public static void main(String[] args) {
        new Thread(new ConnectionPerThread()).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.SOCKET_SERVER_PORT);
            System.out.println("server start on port: " + Constants.SOCKET_SERVER_PORT);
            while (!Thread.interrupted()) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Handler implements Runnable {

        public final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // echo
                    byte[] input = new byte[1024];
                    socket.getInputStream().read(input);
                    System.out.println("receive message: " + new String(input));
                    socket.getOutputStream().write(input);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
