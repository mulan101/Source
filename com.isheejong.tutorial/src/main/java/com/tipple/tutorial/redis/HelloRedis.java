package com.tipple.tutorial.redis;

import redis.clients.jedis.Jedis;

public class HelloRedis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");

		jedis.set("tutorial-name", "Redis tutorial");
		System.out.println("Stored string in redis:: " + jedis.get("tutorial-name"));
		
		
	}
}
