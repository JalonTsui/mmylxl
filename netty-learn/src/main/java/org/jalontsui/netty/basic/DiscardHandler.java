package org.jalontsui.netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.ArrayList;

@Slf4j
public class DiscardHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        ArrayList<Byte> bytes = new ArrayList<>();
        while (in.isReadable()) {
            byte b = in.readByte();
            bytes.add(b);
        }
        ByteBuffer buffer = ByteBuffer.allocate(bytes.size());
        for (Byte b : bytes) {
            buffer.put(b);
        }
        byte[] arr = buffer.array();
        log.info("收到消息：{}", new String(arr, 0, arr.length));

        super.channelRead(ctx, msg);
    }
}
