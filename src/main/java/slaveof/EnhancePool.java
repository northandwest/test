package slaveof;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisPool;

public class EnhancePool extends JedisPool {

	public EnhancePool(GenericObjectPoolConfig poolConfig, String host,int port) {
		super(poolConfig, host);
	}

}
