package rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	private EventLoopGroup loop = new NioEventLoopGroup();
	private String ip;
	private int port;
	
	Client(String ip,int port)
	{
		this.ip = ip;
		this.port = port;
	}
	
	public static void main(String[] args) {
		new Client("127.0.0.1",14527).run();
		
		new Client("127.0.0.1",14527).run();
	}

	public Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {
		System.out.println("-----");
		
		if (bootstrap != null) {
			final MyInboundHandler handler = new MyInboundHandler(this);
			bootstrap.group(eventLoop);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(handler);
				}
			});
			bootstrap.remoteAddress(ip, this.port);
			ChannelFuture connect = bootstrap.connect();//
			connect.addListener(new ReConnectionListener(this));
			
			Channel channel = connect.channel();
//			channel.writeAndFlush("hello server");
			try {
				channel.closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		return bootstrap;
	}

	public void run() {
		createBootstrap(new Bootstrap(), loop);
	}
}
