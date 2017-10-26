package com.platform.common.redis;

import com.google.common.collect.Maps;
import com.platform.common.utils.PropertiesFileUtil;
import com.platform.common.utils.PropertiesLoader;
import com.platform.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 工具类
 * Created by shuzheng on 2016/11/26.
 */
public class RedisUtil {

	protected static ReentrantLock lockPool = new ReentrantLock();
	protected static ReentrantLock lockJedis = new ReentrantLock();

	private static Logger _log = LoggerFactory.getLogger(RedisUtil.class);

	// Redis服务器IP
	private static String IP = PropertiesFileUtil.getInstance("redis").get("master.redis.ip");

	// Redis的端口号
	private static int PORT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.port");

	// 访问密码
	private static String PASSWORD ="";
	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_active");

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_idle");

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_wait");

	// 超时时间
	private static int TIMEOUT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.timeout");

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = false;

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60;            //一小时
	public final static int EXRP_DAY = 60 * 60 * 24;        //一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30;    //一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
		} catch (Exception e) {
			_log.error("First create JedisPool error : " + e);
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (null == jedisPool) {
			initialPool();
		}
	}


	/**
	 * 同步获取Jedis实例
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		poolInit();
		Jedis jedis = null;
		try {
			if (null != jedisPool) {
				jedis = jedisPool.getResource();
				try {
					jedis.auth(PASSWORD);
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			_log.error("Get jedis error : " + e);
		}
		return jedis;
	}

	/**
	 * 设置 String
	 * @param key
	 * @param value
	 */
	public synchronized static void set(String key, String value) {
		try {
			value = StringUtils.isBlank(value) ? "" : value;
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 byte[]
	 * @param key
	 * @param value
	 */
	public synchronized static void set(byte[] key, byte[] value) {
		try {
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 String 过期时间
	 * @param key
	 * @param value
	 * @param seconds 以秒为单位
	 */
	public synchronized static void set(String key, String value, int seconds) {
		try {
			value = StringUtils.isBlank(value) ? "" : value;
			Jedis jedis = getJedis();
			jedis.setex(key, seconds, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set keyex error : " + e);
		}
	}

	/**
	 * 设置 byte[] 过期时间
	 * @param key
	 * @param value
	 * @param seconds 以秒为单位
	 */
	public synchronized static void set(byte[] key, byte[] value, int seconds) {
		try {
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.expire(key, seconds);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set key error : " + e);
		}
	}

	/**
	 * 获取String值
	 * @param key
	 * @return value
	 */
	public synchronized static String get(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	/**
	 * 获取byte[]值
	 * @param key
	 * @return value
	 */
	public synchronized static byte[] get(byte[] key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		byte[] value = jedis.get(key);
		jedis.close();
		return value;
	}

	/**
	 * 删除值
	 * @param key
	 */
	public synchronized static void remove(String key) {
		try {
			Jedis jedis = getJedis();
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			_log.error("Remove keyex error : " + e);
		}
	}

	/**
	 * 删除值
	 * @param key
	 */
	public synchronized static void remove(byte[] key) {
		try {
			Jedis jedis = getJedis();
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			_log.error("Remove keyex error : " + e);
		}
	}

	/**
	 * lpush
	 * @param key
	 * @param key
	 */
	public synchronized static void lpush(String key, String... strings) {
		try {
			Jedis jedis = RedisUtil.getJedis();
			jedis.lpush(key, strings);
			jedis.close();
		} catch (Exception e) {
			_log.error("lpush error : " + e);
		}
	}

	/**
	 * lrem
	 * @param key
	 * @param count
	 * @param value
	 */
	public synchronized static void lrem(String key, long count, String value) {
		try {
			Jedis jedis = RedisUtil.getJedis();
			jedis.lrem(key, count, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("lpush error : " + e);
		}
	}

	/**
	 * sadd
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public synchronized static void sadd(String key, String value, int seconds) {
		try {
			Jedis jedis = RedisUtil.getJedis();
			jedis.sadd(key, value);
			jedis.expire(key, seconds);
			jedis.close();
		} catch (Exception e) {
			_log.error("sadd error : " + e);
		}
	}

	/**
	 * incr
	 * @param key
	 * @return value
	 */
	public synchronized static Long incr(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		long value = jedis.incr(key);
		jedis.close();
		return value;
	}

	/**
	 * decr
	 * @param key
	 * @return value
	 */
	public synchronized static Long decr(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		long value = jedis.decr(key);
		jedis.close();
		return value;
	}
	/**
	 * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
	 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
	 分数值可以是整数值或双精度浮点数。
	 如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
	 当 key 存在但不是有序集类型时，返回一个错误。
	 * @param string
	 * @param i
	 * @param string2
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 */
	public static Long zadd(String key, double score, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.zadd(key, score, member);
		jedis.close();
		return result;
	}
	/**
	 * Redis Zrevrangebyscore 返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。
	 具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。
	 除了成员按分数值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
	 */
	public static LinkedHashSet<String> zrevrangebyscore(String key, String max, String min, int offset, int count){
		Jedis jedis = getJedis();
		LinkedHashSet<String> result = (LinkedHashSet<String>) jedis.zrevrangeByScore(key, max, min, offset, count);
		jedis.close();
		return result;
	}
	/**
	 * Redis Hmget 命令用于返回哈希表中，一个或多个给定字段的值。
	 如果指定的字段不存在于哈希表，那么返回一个 nil 值。
	 * @param key
	 * @param item
	 * @return 一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样。
	 */
	public static List<String> hmget(String key, String... item) {
		Jedis jedis = getJedis();
		List<String> result = jedis.hmget(key, item);
		jedis.close();
		return result;
	}
	public static Long hset(String key, String item, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.hset(key, item, value);
		jedis.close();
		return result;
	}
}