package rpc.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import rpc.server.ObjectCodec;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 注册IO事件处理类
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjectCodec());
//        ch.pipeline().addLast(new ClientRequestResponseHander());
    }
}