package com.gaocongbo.redis.test;

import org.junit.Test;

import com.gaocongbo.common.utils.RandomUtil;

/**
 * @作者：高淙博
 * @创建时间：2019年11月11日下午8:13:18
 *
 */

public class WeekUtil {

	@Test
	public static String sexaa() {
		int random = RandomUtil.random(1, 2);
		if (random == 1) {
			System.out.println("男");
		} else {
			System.out.println("女");
		}
		return null;
	}
}
