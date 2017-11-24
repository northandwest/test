package rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyNIOServer {
	public static void main(String[] args) throws InterruptedException {

		// 1.创建NIOServerSocketChannel的服务引导
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		// 2.创建线程池 boss(请求转发) worker(IO事件处理)
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		// 3.绑定线程
		serverBootstrap.group(boss, worker);
		// 4.设置ServerSocket服务类
		serverBootstrap.channel(NioServerSocketChannel.class);
		// 5.绑定IO处理事件
		serverBootstrap.childHandler(new ServerChannelInitializer());
		
		// 6.绑定服务端口
		System.out.println("服务器监听14527端口");
		ChannelFuture future = serverBootstrap.bind(14527).sync();
		// 等待服务器被关闭
		future.channel().closeFuture().sync();

		// 释放线程资源
		worker.shutdownGracefully();
		boss.shutdownGracefully();
	}
}
