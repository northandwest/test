package rpc.client;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

public class ReConnectionListener implements ChannelFutureListener {
	private NettyNIOClient client;
	private static int retryTimes = 0;
	private static int maxRetryTimes = 30;
	private static long interval = 1l;

	public ReConnectionListener(NettyNIOClient client,long interval) {
		this.client = client;
		this.interval = interval;
	}

	public ReConnectionListener(NettyNIOClient client) {
		this.client = client;
	}
	
	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
		if (!channelFuture.isSuccess()) {
			System.out.println("Reconnect @ "+new Date().toLocaleString());
			
			final EventLoop loop = channelFuture.channel().eventLoop();
			loop.schedule(new Runnable() {
				@Override
				public void run() {
					if (maxRetryTimes >= retryTimes) {
						
						client.createBootstrap(new Bootstrap(), loop);
						retryTimes++;
						
						System.out.println("Reconnect" + retryTimes);
					} else {
						System.out.println("not execute");
					}
				}
			}, interval, TimeUnit.SECONDS);
		}
	}
}