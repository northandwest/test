package rpc.server;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerRequestResponseHander extends SimpleChannelInboundHandler {
    /**
     * 异常调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
    	SocketAddress remoteAddress = ctx.channel().remoteAddress();
        System.out.println(remoteAddress.toString()+"发生异常了···异常信息："+cause.getMessage());
        ctx.close();
    }

    /**
     * 读数据调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
    	SocketAddress remoteAddress = ctx.channel().remoteAddress();

        System.out.println(remoteAddress.toString()+"客户端发请求了···服务器接收的数据："+msg);
        ctx.writeAndFlush(msg);//写回响应
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		  System.out.println("0客户端发请求了···服务器接收的数据："+msg);
	        ctx.writeAndFlush(msg);//写回响应
	}
}