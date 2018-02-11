package sentinel;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class App {
	public static void main(String[] args) {
		Set<String> sentinels = new HashSet<String>();
		sentinels.add("172.18.18.207:26379");
		
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);

		Jedis jedis = pool.getResource(); 

		jedis.set("jedis", "jedis");

		jedis.close();
	}
}
