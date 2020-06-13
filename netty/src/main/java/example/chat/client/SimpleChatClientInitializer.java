package example.chat.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class SimpleChatClientInitializer extends ChannelInitializer<SocketChannel> {

    private SimpleChatClient chatClient;

    public SimpleChatClientInitializer(SimpleChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("idle", new IdleStateHandler(0, 0, 20, TimeUnit.SECONDS));
        pipeline.addLast("heardBeat", new SimpleHeartBeatClientHandler(this.chatClient));

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new SimpleChatClientHandler());

    }
}