package com.bucuoa.mutili;

import java.util.concurrent.Semaphore;

public class BankService {

	public static void main(String[] args) {
		
		Runnable customer = new Runnable() {
			final Semaphore availableWindow = new Semaphore(5, true);
			int count = 1;

			@Override
			public void run() {
				int time = (int) (Math.random() * 10 + 3);
				int num = count++;
				
				try {
					availableWindow.acquire();
					
					System.out.println("正在为第【" + num + "】个客户办理业务，需要时间：" + time + "s！");
					Thread.sleep(time * 1000);
					
					if (availableWindow.hasQueuedThreads()) {
						System.out.println("第【" + num + "】个客户已办理完业务，有请下一位！");
					} else {
						System.out.println("第【" + num + "】个客户已办理完业务，没有客户了，休息中！");
					}
					
					availableWindow.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		for (int i = 1; i <= 20; i++) {
			new Thread(customer).start();
		}
	}
}