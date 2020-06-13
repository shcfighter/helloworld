package example.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class SimpleChatClient {
    public static EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap bootstrap;
    public Channel channel;
    private final String host;
    private final int port;

    public SimpleChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        new SimpleChatClient("localhost", 8080).run();
    }

    public void run() {
        try {
            bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer(SimpleChatClient.this));

            doConnect();

            /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    channel.writeAndFlush(in.readLine() + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    protected void doConnect() {
        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = bootstrap.connect("localhost", 12345);

        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    channel = futureListener.channel();
                    System.out.println("Connect to server successfully!");
                    say();
                } else {
                    System.out.println("Failed to connect to server, try connect after 10s");
                    System.out.println(futureListener.channel().eventLoop());
                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("redoConnect");
                            doConnect();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
    }

    public void say(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                channel.writeAndFlush(in.readLine() + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
