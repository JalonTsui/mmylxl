package org.jalontsui.io.reactor;

import lombok.extern.slf4j.Slf4j;
import org.jalontsui.io.utils.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MultiThreadEchoServerReactor {

    public static void main(String[] args) throws IOException {
        MultiThreadEchoServerReactor server = new MultiThreadEchoServerReactor();
        server.startService();
    }

    private ServerSocketChannel serverSocket;
    private AtomicInteger next = new AtomicInteger(0);
    // 选择器集合，引入多个选择器
    Selector[] selectors = new Selector[2];
    // 引入多个子反应器
    SubReactor[] subReactors = null;

    public MultiThreadEchoServerReactor() throws IOException {
        selectors[0] = Selector.open(); // 第一个selector负责监听链接事件
        selectors[1] = Selector.open(); // 第二个selector负责监听传输事件
        serverSocket = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(Constants.SOCKET_SERVER_PORT);
        serverSocket.configureBlocking(false);
        serverSocket.bind(address);
        // 绑定链接事件到selector[0]中
        SelectionKey sk = serverSocket.register(selectors[0], SelectionKey.OP_ACCEPT);
        log.info("服务启动成功，端口：{}", Constants.SOCKET_SERVER_PORT);
        // 绑定handler到sk上，处理链接
        sk.attach(new AcceptorHandler());
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        SubReactor subReactor2 = new SubReactor(selectors[1]);
        subReactors = new SubReactor[]{subReactor1, subReactor2};
    }

    // 启动服务
    public void startService() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    // 子反应器，负责事件分发，但是不处理事件
    private class SubReactor implements Runnable {

        private final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                // 为什么把selector.select() > 0加在循环上读不到read事件
                while (!Thread.interrupted()) { // todo 好好理解一下interrupted
                    selector.select();
                    Set<SelectionKey> skSet = selector.selectedKeys();
                    Iterator<SelectionKey> it = skSet.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        dispatch(sk);
                    }
                    skSet.clear();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void dispatch(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment();
            if (handler != null) {
                handler.run();
            }
        }
    }

    // 新链接处理器
    private class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                // 创建传输处理器，并且将传输通道注册到选择器2上
                if (channel != null) {
                    new MultiThreadEchoHandler(selectors[1], channel);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class MultiThreadEchoHandler implements Runnable {
        private SocketChannel channel;
        private SelectionKey sk;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        private static final int RECEIVED = 0;
        private static final int SENDING = 1;
        private int state = RECEIVED;
        // 线程池，demo直接使用executor创建
        private static ExecutorService pool = Executors.newFixedThreadPool(4);

        public MultiThreadEchoHandler(Selector selector, SocketChannel channel) throws IOException {
            this.channel = channel;
            channel.configureBlocking(false);
            sk = channel.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ); // todo 有什么用
            selector.wakeup(); // todo 有什么用
        }

        @Override
        public void run() {
            pool.submit(() -> asyncRun());
        }

        public synchronized void asyncRun() {
            try {
                if (state == SENDING) {
                    channel.write(byteBuffer);
                    byteBuffer.clear();
                    sk.interestOps(SelectionKey.OP_READ);
                    state = RECEIVED;
                } else if (state == RECEIVED) {
                    int len = 0;
                    while ((len = channel.read(byteBuffer)) > 0) {
                        log.info("接受到消息：{}", new String(byteBuffer.array(), 0, len));
                    }
                    byteBuffer.flip();
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
