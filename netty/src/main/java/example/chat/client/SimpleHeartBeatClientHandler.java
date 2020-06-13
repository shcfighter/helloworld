package example.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

public class SimpleHeartBeatClientHandler extends ChannelInboundHandlerAdapter {

    private SimpleChatClient chatClient;

    public SimpleHeartBeatClientHandler(SimpleChatClient chatClient) {
        this.chatClient = chatClient;
    }

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
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> {
            System.out.println("channelInactive doConnect");
            chatClient.doConnect();
        }, 10, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelUnregistered();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelReadComplete();
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelWritabilityChanged();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
