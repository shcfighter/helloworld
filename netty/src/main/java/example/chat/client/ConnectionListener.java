package example.chat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

public class ConnectionListener implements ChannelFutureListener {
    private SimpleChatClient client;

    public ConnectionListener(SimpleChatClient client) {
        System.out.println("ConnectionListener");
        this.client = client;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {
            System.out.println("Failed to connect to server, try connect after 10s");
            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(new Runnable() {
                @Override
                public void run() {
                    client.run();
                }
            }, 10L, TimeUnit.SECONDS);
        } else {
            client.channel = channelFuture.channel();
            System.out.println("Connect to server successfully!");
            this.client.say();
        }
    }
}