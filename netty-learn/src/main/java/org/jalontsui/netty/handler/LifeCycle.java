package org.jalontsui.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class LifeCycle {

    public static void main(String[] args) {
        InHandlerDemo inHandlerDemo = new InHandlerDemo(1);
        InHandlerDemo inHandlerDemo2 = new InHandlerDemo(2);
        InHandlerDemo inHandlerDemo3 = new InHandlerDemo(3);
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                channel.pipeline().addLast(inHandlerDemo);
                channel.pipeline().addLast(inHandlerDemo2);
                channel.pipeline().addLast(inHandlerDemo3);
            }
        };
        // EmbeddedChannel是一个netty测试pipeline的测试类，不需要每次都启动netty服务器来验证pipeline的出入参是否复合预期
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        channel.writeInbound(buf);
        channel.flush();
        channel.writeInbound(buf);
        channel.flush();
        channel.close();
        // 每个handler对应一个自己的上下文对象，并非一个流水线对应一个上下文对象
        log.info("InHandlerDemo size: {}", InHandlerDemo.ctxList.size());
    }


    /**
     * 事件的触发机制是：
     * 有多个handler，每个handler的事件都是按照生命周期触发的，多个handler的同一事件按照绑定顺序触发（出站执行顺序与绑定相反）
     * 例子：
     * handler1
     * -> event1
     * -> event2
     * <p>
     * handler2
     * -> event1
     * -> event2
     * <p>
     * handler3
     * -> event1
     * -> event2
     * <p>
     * 按照handler1 - handler2 - handler3顺序注册
     * 输出顺序：
     * handler1 - event1
     * handler2 - event1
     * handler3 - event1
     * handler1 - event2
     * handler2 - event2
     * handler3 - event2
     * handler1 - event3
     * handler2 - event3
     * handler3 - event3
     */
    public static class InHandlerDemo extends ChannelInboundHandlerAdapter {

        public static Set<ChannelHandlerContext> ctxList = new HashSet<>();

        private int demoNum;

        public InHandlerDemo(int num) {
            super();
            this.demoNum = num;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            ctxList.add(ctx);
            log.info("channelRegistered trigger - {}", demoNum);
            ctx.fireUserEventTriggered(new Object()); // 会触发所有后面handler的userEventTriggered事件
            // 如果不调用父类的方法，这次的事件不会传递到下一个handler的channelRegistered事件上
            // super.channelRegistered(ctx);
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            ctxList.add(ctx);
            log.info("channelUnregistered trigger - {}", demoNum);
            super.channelUnregistered(ctx);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("channelActive trigger - {}", demoNum);
            super.channelActive(ctx);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            log.info("channelInactive trigger - {}", demoNum);
            super.channelInactive(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("channelRead trigger - {}", demoNum);
            super.channelRead(ctx, msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info("channelReadComplete trigger - {}", demoNum);
            super.channelReadComplete(ctx);
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            log.info("userEventTriggered trigger - {}", demoNum);
            super.userEventTriggered(ctx, evt);
        }

        @Override
        public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
            log.info("channelWritabilityChanged trigger - {}", demoNum);
            super.channelWritabilityChanged(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.info("exceptionCaught trigger - {}", demoNum);
            super.exceptionCaught(ctx, cause);
        }
    }
}
