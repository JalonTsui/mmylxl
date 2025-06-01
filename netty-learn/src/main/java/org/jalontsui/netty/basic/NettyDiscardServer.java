package org.jalontsui.netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.netty.entity.constants.Constants;

@Slf4j
public class NettyDiscardServer {
    // bootstrap: 辅助程序
    // ServerBootstrap：netty的引导类，负责组装netty服务器或客户端的启动流程，channel，selector
    private ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer() {
    }

    public void runServer() {
        // 创建反应器轮询组 旧的 NioEventLoopGroup api 已经被弃用
        /**
         * netty中父子通道的概念：
         * 父通道：负责服务器链接监听和接收的监听通道（如NioServerSocketChannel）也叫父通道（Parent Channel）
         * 子通道：每一个接收到的传输类通道（如：NioSocketChannel）也叫子通道（Child Channel）
         */
        // 一个EventLoop(事件轮询监听器)就相当于一个SubReactor，一个EventLoopGroup就是多线程版本的SubReactor

        // bossLoopGroup负责监听和接收链接
        EventLoopGroup bossLoopGroup = new MultiThreadIoEventLoopGroup(1, NioIoHandler.newFactory());
        // workerLoopGroup负责处理业务handler，不指定多少个线程，默认就是 cpu核数 * 2
        EventLoopGroup workerLoopGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
        try {
            // 1. 设置反应器轮询组 (第一个参数为boss组，负责监听和接收链接，第二个为worker组，负责处理handler)
            // ps: 如果不需要分开监听和处理逻辑，也可以只传入一个EventLoopGroup
            b.group(bossLoopGroup, workerLoopGroup);
            // 2. 设置nio类型通道
            b.channel(NioServerSocketChannel.class);
            // 3. 设置监听端口
            b.localAddress(Constants.SERVER_PORT);
            // 4. 设置通道参数, option 是给父通道设置参数，childOption是给子通道设置参数
            b.option(ChannelOption.SO_KEEPALIVE, true);
            // 4.5. 装配父通道的流水线，但是父通道都是监听和接收逻辑，底层就已经封装好了，一般不需要配置
            // b.handler(new ChannelInitializer<ServerSocketChannel>() {
            //     @Override
            //     protected void initChannel(ServerSocketChannel channel) throws Exception {
            //
            //     }
            // });
            // 5. 装配子通道流水线(pipeline), 其实就是接收到一个请求后，会进入流水线，按照顺序执行对应的handler
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel c) throws Exception {
                    ChannelPipeline p = c.pipeline();
                    p.addLast(new DiscardHandler());
                }
            });
            // 6. 绑定服务器
            // ps: 关于Future异步回调或者同步阻塞可以参考书籍《Java高并发核心编程　卷2：多线程、锁、JMM、JUC、高并发设计模式》
            // 简单来说，也是创建了一个线程，然后可以通过Future来判断线程的执行状态，这里调用sync()可以理解为等待，直到绑定完成
            ChannelFuture channelFuture = b.bind().sync();

            // 下面这个写法也是一样的效果
            // ChannelFuture channelFuture = b.bind();
            // while (!channelFuture.isDone()) {
            //     log.info("waiting...");
            // }

            log.info("服务启动成功，监听端口：{}", channelFuture.channel().localAddress());
            // 7. 等待通道关闭的异步任务结束,对于服务器来说就是 NioServerSocketChannel, 调用其close方法即可关闭
            Channel channel = channelFuture.channel();
            log.info("{}", channel.getClass());
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyDiscardServer().runServer();
    }
}
