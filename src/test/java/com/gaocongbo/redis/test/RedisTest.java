package com.gaocongbo.redis.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.gaocongbo.common.utils.RandomUtil;

import com.gaocongbo.common.utils.StringUtil;

/**
 * @作者：高淙博
 * @创建时间：2019年11月11日下午8:13:18
 *
 */
//启动spring-redis.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class RedisTest {

	@Resource
	private RedisTemplate redisTemplate;

	@Test
	public void test1() {
		// 创建list 集合
		List<User> list = new ArrayList<User>();

		// 生成50000条 User对象
		for (int i = 1; i <= 50000; i++) {
			// (2) 姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			String name = StringUtil.generateChineseName();
			// (3) 性别在女和男两个值中随机。（4分）
			String sex = WeekUtil.sexaa();

			// (4) 手机以13开头+9位随机数模拟。（4分）
			String phone = "13" + RandomUtil.randomNumber(9);
			// (5)邮箱以3-20个随机字母 + @qq.com（4分）
			String email = StringUtil.randomChineseString() + "@qq.cpm";
			// (6) 生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			String birthday = "2019-12-09";
			User user = new User(i, name, sex, phone, email, birthday);

			list.add(user);
		}
		for (User user : list) {
			System.out.println(user);
		}
	}

	// 4. 使用JDK系列化方式保存5万个user随机对象到Redis，并计算耗时（18 分）

	// 测试JSON系列化
	@Test
	public void testJDK() {
		// 创建list 集合

		List<User> list = new ArrayList<User>();

		// 生成50000条 User对象
		for (int i = 1; i <= 50000; i++) {
			// (2) 姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			String name = StringUtil.generateChineseName();
			// (3) 性别在女和男两个值中随机。（4分）
			/* String sex = WeekUtil.sexaa(); */
			String sex = "男";
			// (4) 手机以13开头+9位随机数模拟。（4分）
			String phone = "13" + RandomUtil.randomNumber(9);
			// (5)邮箱以3-20个随机字母 + @qq.com（4分）
			String email = StringUtil.randomChineseString() + "@qq.cpm";
			// (6) 生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			String birthday = "2019-12-09";
			User user = new User(i, name, sex, phone, email, birthday);
			list.add(user);
		}

		// 存入数据
		ListOperations opsForList = redisTemplate.opsForList();
		// 开始毫秒
		long start = System.currentTimeMillis();
		//存入redis
		opsForList.leftPush("jdk", list);
		// 结束毫秒
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	// 测试JSON系列化
	@Test
	public void testJSON() {
		// 创建list 集合

		List<User> list = new ArrayList<User>();

		// 生成50000条 User对象
		for (int i = 1; i <= 50000; i++) {
			// (2) 姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			String name = StringUtil.generateChineseName();
			// (3) 性别在女和男两个值中随机。（4分）
			/* String sex = WeekUtil.sexaa(); */
			String sex = "男";
			// (4) 手机以13开头+9位随机数模拟。（4分）
			String phone = "13" + RandomUtil.randomNumber(9);
			// (5)邮箱以3-20个随机字母 + @qq.com（4分）
			String email = StringUtil.randomChineseString() + "@qq.cpm";
			// (6) 生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			String birthday = "2019-12-09";
			User user = new User(i, name, sex, phone, email, birthday);
			list.add(user);
		}

		// 存入数据
		ListOperations opsForList = redisTemplate.opsForList();
		// 开始毫秒
		long start = System.currentTimeMillis();
		//存入redis
		opsForList.leftPush("hash", list);
		// 结束毫秒
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	// 测试Hash
	@Test
	public void testHash() {
		Map<String, User> hashMap = new HashMap<String, User>();

		// 生成50000条 User对象
		for (int i = 1; i <= 50000; i++) {
			// (2) 姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			String name = StringUtil.generateChineseName();
			// (3) 性别在女和男两个值中随机。（4分）
			/* String sex = WeekUtil.sexaa(); */
			String sex = "男";
			// (4) 手机以13开头+9位随机数模拟。（4分）
			String phone = "13" + RandomUtil.randomNumber(9);
			// (5)邮箱以3-20个随机字母 + @qq.com（4分）
			String email = StringUtil.randomChineseString() + "@qq.cpm";
			// (6) 生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			String birthday = "2019-12-09";
			User user = new User(i, name, sex, phone, email, birthday);
			hashMap.put(i + "", user);
		}
		// 存入数据
		HashOperations opsForHash = redisTemplate.opsForHash();
		// 开始毫秒
		long start = System.currentTimeMillis();
		//存入redis
		opsForHash.putAll("hash", hashMap);
		// 结束毫秒
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
