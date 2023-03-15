package hbv;
import redis.clients.jedis.*;

public class MyJedisPool {
	static JedisPool jedisPool; 

	public static void init(String host, int port, String password){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(800);
		poolConfig.setMaxIdle(800);
		poolConfig.setMinIdle(600);
		jedisPool = new JedisPool(poolConfig,host, port, null, password);

	}
	public static void destroy(){
		jedisPool.close();
	}

	public static Jedis getJedis(){
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}
	public static void releaseJedis(Jedis jedis){
		jedis.close();
	}

}
