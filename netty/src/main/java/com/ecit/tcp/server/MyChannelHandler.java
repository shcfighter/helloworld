package com.ecit.tcp.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyChannelHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("hello " + msg);
        //Thread.sleep(30000L);
        ctx.writeAndFlush("你好，" + msg);
    }
}
