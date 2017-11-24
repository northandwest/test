package rpc;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;

@SuppressWarnings("rawtypes")
public class MyInboundHandler extends SimpleChannelInboundHandler {
	private Client client;

	public MyInboundHandler(Client client) {
		this.client = client;
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		final EventLoop eventLoop = ctx.channel().eventLoop();
		long time = 1l;

		eventLoop.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("@@@@@@@@@@@@");
				client.createBootstrap(new Bootstrap(), eventLoop);

			}
		}, time, TimeUnit.SECONDS);
		super.channelInactive(ctx);
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("RpcClientHandler channelActive:" + ctx.name());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.write("hello");
		System.out.println(msg+"=>hello");
	}
}