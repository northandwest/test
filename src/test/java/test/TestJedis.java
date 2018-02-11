package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import slaveof.JedisUtils;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "classpath:/spring-redis-slave-context.xml" }) // 加载配置文件
public class TestJedis {
	@Autowired
	private JedisUtils jedisUtils;
	@Autowired
	private  JedisPool jedisPool; 
	@Test
	public void testInsert() {
		
	
		Jedis jedis = jedisPool.getResource();
		jedis.set("jedis", "2.9.0");

		String result = jedis.get("jedis");
		String result2 = jedisUtils.get("jedis");
		System.out.print("jedis==>" + result+","+result2);
	}
//	@Test
//	public void testPool() {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(30);
//		config.setMaxIdle(10);
//		// config.setMaxWait(1000);
//		config.setTestOnBorrow(false);
//		config.setTestOnReturn(false);
//		config.setTestWhileIdle(true);
//		config.setTimeBetweenEvictionRunsMillis(30000);
//		config.setNumTestsPerEvictionRun(10);
//		config.setMinEvictableIdleTimeMillis(60000);
//		JedisPool pool = new JedisPool(config, "192.168.149.31", 6379);
//		
//		Jedis jedis = pool.getResource();
//		
//		jedis.set("j","2.9.0");
//		String result = jedis.get("j");
//		System.out.print("j==>" + result);
//		jedis.close();
//	}
}
