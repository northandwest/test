package rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import rpc.entity.User;

/**
 * 事件处理类
 * @author MOTUI
 *
 */
public class ClientRequestResponseHander extends SimpleChannelInboundHandler {
	private NettyNIOClient client;

	public ClientRequestResponseHander(NettyNIOClient client)
	{
		this.client = client;
	}
    /**
     * 异常调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("发生异常了···重新连接："+cause.getMessage());
//        ctx.close();
    	client.createBootstrap(new Bootstrap(), ctx.channel().eventLoop());

    }

    public void channelInActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInActive："+ctx.name());

    	client.createBootstrap(new Bootstrap(), ctx.channel().eventLoop());

    }
    /**
     * 发送请求
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(new User(1, "guozh"));
		super.channelActive(ctx);

    }


    /**
     * 读数据调用
     */
//    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        System.out.println("客户端接收的响应："+msg);
        //关闭链接
//        ctx.close();

    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub

        System.out.println("客户端接收的响应0："+msg);
        //关闭链接
//        ctx.close();
	}
}