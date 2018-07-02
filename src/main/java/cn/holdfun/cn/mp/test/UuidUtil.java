package cn.holdfun.cn.mp.test;

import java.util.UUID;

public class UuidUtil {
	public static String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
