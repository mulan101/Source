package com.tipple.tutorial.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;



public class HelloRedis { 
	public static void main(String args[]){ 
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1:6379"); 
		Jedis jedis = jedisPool.getResource(); 
		try{ 
			jedis.set("test1", "testValue1"); 
			jedis.set("test2", "testValue2"); 
			System.out.println("jedis Value 1 : " + jedis.get("test1")); 
			System.out.println("jedis Value 2 : " + jedis.get("test2")); 
			System.out.println(jedis.dbSize()); 
		}catch(JedisConnectionException e){ 
			if(null != jedis){ 
				jedisPool.returnBrokenResource(jedis); 
		                jedis = null; 
			} 
		}finally{ 
			if(null != jedis){ 
				jedisPool.returnResource(jedis); 
			} 
		} 
		jedisPool.destroy(); 
	} 
}