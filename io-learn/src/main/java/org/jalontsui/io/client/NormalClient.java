package org.jalontsui.io.client;

import org.jalontsui.io.utils.Constants;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/*
 * @author JalonTsui
 * @Date 16:03 2025/5/31
 **/

public class NormalClient {
    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(Constants.LOCAL_HOST_IP, Constants.SOCKET_SERVER_PORT);
        SocketChannel socketChannel = SocketChannel.open(address);
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()) {
            Thread.sleep(10);
        }
        System.out.println("connection success");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello server".getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }
}
