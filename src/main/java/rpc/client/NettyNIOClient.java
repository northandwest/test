package rpc.client;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import rpc.server.ObjectCodec;

public class NettyNIOClient {
	InetSocketAddress serverInfo;
	public NettyNIOClient(InetSocketAddress serverInfo)
	{
		this.serverInfo = serverInfo;
	}

	private EventLoopGroup loop = new NioEventLoopGroup();
//new InetSocketAddress("127.0.0.1", 14527)
	public void createBootstrap(Bootstrap bootstrap, EventLoopGroup loop) {
		System.out.println("connect server:"+serverInfo.getAddress()+":"+serverInfo.getPort());
		final ClientRequestResponseHander clientRequestResponseHander = new ClientRequestResponseHander(this);
		NioEventLoopGroup boss = new NioEventLoopGroup();
		bootstrap.group(boss);
		// 4.设置NioSocketChannel
		bootstrap.channel(NioSocketChannel.class);
		// 5.绑定IO处理事件
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline().addLast(new ObjectCodec());
				socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
				socketChannel.pipeline().addLast(clientRequestResponseHander);
				socketChannel.pipeline().addLast(new HeartBeatClientHandler());
			}
		});
		
		// 6.链接服务器
		ChannelFuture future = bootstrap.connect(serverInfo);
		
		future.addListener(new ReConnectionListener(this));

		// 等待关闭连接
		try {
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 释放资源
//		boss.shutdownGracefully();
	}

	public void run() {
		createBootstrap(new Bootstrap(), loop);
	}

	public static void main(String[] args) throws InterruptedException {
		InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 14527);

		new NettyNIOClient(inetSocketAddress).run();
		
	/*	// 1.创建NIOSocketChannel的服务引导
		Bootstrap bootstrap = new Bootstrap();
		// 2.创建线程池 worker(IO事件处理)
		NioEventLoopGroup boss = new NioEventLoopGroup();
		// 3.关联线程池
		bootstrap.group(boss);
		// 4.设置NioSocketChannel
		bootstrap.channel(NioSocketChannel.class);
		// 5.绑定IO处理事件
		bootstrap.handler(new ClientChannelInitializer());
		// 6.链接服务器
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 14527));

		// 等待关闭连接
		future.channel().closeFuture().sync();

		// 释放资源
		boss.shutdownGracefully();*/
	}
}