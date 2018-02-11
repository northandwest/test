package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import slaveof.JedisUtils;
import slaveof.JedisUtils2;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({"classpath:/spring-redis-slave-context.xml"}) // 加载配置文件
public class TestJedis {
	@Autowired
	private JedisUtils jedisUtils;
	@Autowired
	private JedisUtils2 jedisUtils2;
	@Autowired
	private  JedisPool jedisPooled; 
	
	@Test
	public void testInsert() {
		StopWatch watch = new StopWatch("redis monitor");

		Jedis jedis = jedisPooled.getResource();
		watch.start("set");
//		jedis.set("jedis", "2.9.0");
		watch.stop();
		watch.start("get1");
//		String result = jedis.get("jedis");
		watch.stop();
		watch.start("get2");
//		String result2 = jedisUtils.get("jedis");

		watch.stop();
		
		watch.start("get3");
		String result3 = jedisUtils2.get("jedis");
		result3 = jedisUtils2.get("jedis");
		result3 = jedisUtils2.get("jedis");
		result3 = jedisUtils2.get("jedis");
		result3 = jedisUtils2.get("jedis");
		result3 = jedisUtils2.get("jedis");

		watch.stop();
		
		System.out.println("jedisPool==>" + watch.prettyPrint());

//		System.out.println("jedis==>" + result);
//		System.out.println("jedisUtils==>" + result2);
//		System.out.println("jedisUtils2==>" + result3);

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
