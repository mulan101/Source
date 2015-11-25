package com.tipple.tutorial.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class HelloList {
	public static void main(String[] args) {

		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");

		jedis.lpush("tutorial-list", "Redis");
		jedis.lpush("tutorial-list", "Mongodb");
		jedis.lpush("tutorial-list", "Mysql");

		// Get the stored data and print it
		List<String> list = jedis.lrange("tutorial-list", 0, 5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Stored string in redis:: " + list.get(i));
		}
	}
}