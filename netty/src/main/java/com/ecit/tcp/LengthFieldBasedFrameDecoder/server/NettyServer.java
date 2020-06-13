package com.ecit.tcp.LengthFieldBasedFrameDecoder.server;

import com.ecit.tcp.LengthFieldBasedFrameDecoder.Constants;
import com.ecit.tcp.LengthFieldBasedFrameDecoder.Message;
import com.ecit.tcp.LengthFieldBasedFrameDecoder.MessageDecoder;
import com.ecit.tcp.LengthFieldBasedFrameDecoder.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Ricky Fung
 */
public class NettyServer {

    public void bind(int port) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new MessageDecoder(1<<20, 10, 4));
                            p.addLast(new MessageEncoder());
                            p.addLast(new ServerHandler());
                        }
                    });

            // Bind and start to accept incoming connections.
            ChannelFuture future = b.bind(port).sync(); // (7)

            System.out.println(1<<20);
            // Wait until the server socket is closed.
            future.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ServerHandler extends SimpleChannelInboundHandler<Message> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {


            Message resp = new Message(msg.getMagicType(), msg.getType(), msg.getRequestId(), "Hello world from server");
            ctx.writeAndFlush(resp);
        }
    }

    public static void main(String[] args) throws Exception {

        new NettyServer().bind(Constants.PORT);
    }
}