package rpc.server;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 注册IO事件处理类
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjectCodec());
        ch.pipeline().addLast(new ServerRequestResponseHander());
        ch.pipeline().addLast(new HeartBeatServerHandler());
        ch.pipeline().addLast("ping", new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));  
    }
}