package example.chat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class SimpleHeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent stateEvent = (IdleStateEvent) evt;
        if (evt instanceof IdleStateEvent) {
            switch (stateEvent.state()) {
                case READER_IDLE:{
                    System.out.println("读超时，关闭这个不活跃通道！");
                    ctx.channel().close();
                    break;
                }
                case WRITER_IDLE: {
                    System.out.println("写超时，关闭这个不活跃通道！");
                    ctx.channel().close();
                    break;
                }
                case ALL_IDLE: {
                    System.out.println("读写超时，关闭这个不活跃通道！");
                    ctx.channel().close();
                    break;
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);
    }
}
