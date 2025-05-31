package org.jalontsui.io.reactor;

/*
 * @author JalonTsui
 * @Date 14:38 2025/5/31
 **/

import org.jalontsui.io.handler.EchoHandler;
import org.jalontsui.io.utils.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 单线程的Reactor
 * Reactor模式中有Reactor和Handler两个重要的组件：
 * (1)Reactor：
 * 负责查询IO事件，当检测到一个IO事件时将其发送给相应的Handler处理器去处理。这里的IO事件就是NIO中选择器查询出来的通道IO事件。
 * (2)Handler：
 * 与IO事件（或者选择键）绑定，负责IO事件的处理，完成真正的连接建立、通道的读取、处理业务逻辑、负责将结果写到通道等。
 * <p>
 * Java NIO(java nio其实是io多路复用，并不是真正的nio)的核心三个概念
 * 1. Buffer
 * 2. Channel
 * 3. Selector
 */

public class SingleThreadEchoServerReactor implements Runnable {

    public static void main(String[] args) {
        try {
            new Thread(new SingleThreadEchoServerReactor()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Selector selector;
    private ServerSocketChannel serverSocket;

    SingleThreadEchoServerReactor() throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress(Constants.SOCKET_SERVER_PORT));
        System.out.println("server starting on port: " + Constants.SOCKET_SERVER_PORT);
        // 把socket注册到事件监听器selector中，当接受到请求时selector会往其Set集合中添加SelectionKey
        SelectionKey key = serverSocket.register(this.selector, SelectionKey.OP_ACCEPT);
        key.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            // selector.select(); // selector获取已经准备就绪的事件
            while (!Thread.interrupted() && selector.select() > 0) {
                Set<SelectionKey> selected = selector.selectedKeys(); // 已经准备就绪的事件集合
                Iterator<SelectionKey> iterator = selected.iterator(); // 获取已经监听到的事件
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    dispatch(key); // 1. 派发事件
                }
                selected.clear(); // 清空事件集，避免下次收到请求重复处理
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable handler = (Runnable) key.attachment(); // 2. 调用dispatch时，是selector已经接受到请求了
        if (handler != null) {
            handler.run();
        }
    }

    public class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                // 3. selector已经返回了请求标识，所以这里的accept返回的channel一定是有值的
                SocketChannel channel = serverSocket.accept();
                if (channel != null) {
                    new EchoHandler(selector, channel);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
