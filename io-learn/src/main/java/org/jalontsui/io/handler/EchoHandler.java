package org.jalontsui.io.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/*
 * @author JalonTsui
 * @Date 16:03 2025/5/31
 **/

public class EchoHandler implements Runnable {

    private final SocketChannel channel;
    private final SelectionKey sk;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private static final int RECEiVED = 0;
    private static final int SENDING = 1;
    private int state = RECEiVED;

    public EchoHandler(Selector selector, SocketChannel c) throws IOException {
        this.channel = c;
        c.configureBlocking(false);
        sk = channel.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (state == SENDING) {
                channel.write(byteBuffer);
                byteBuffer.clear();
                sk.interestOps(SelectionKey.OP_READ);
                state = RECEiVED;
            } else if (state == RECEiVED) {
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {
                    System.out.println("receive message: " + new String(byteBuffer.array(), 0, length));
                    System.out.println("receive message length: " + length);
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
