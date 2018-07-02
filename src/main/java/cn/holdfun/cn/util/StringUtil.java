package cn.holdfun.cn.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	private static final String CONVERT_PHONE_STARS = "****";

	public static String convertPhone(String phone) {
		if (StringUtils.isEmpty(phone) || phone.trim().length() < 11)
			return "";
		return phone.trim().replaceFirst(phone.substring(3, 7), CONVERT_PHONE_STARS);
	}

	public static String convertName(String name) {
		if (StringUtils.isEmpty(name) || name.trim().length() < 2)
			return "";
		return name.trim().replaceFirst(name.substring(1, 2), "*");
	}

	/**
	 * 将数字转换成小写字母
	 * 
	 * @param input
	 * @return
	 */
	public static String numToLowercase(int input) {
		return String.valueOf((char) (input + 97));
	}

	/**
	 * 将数字转换成大写字母
	 * 
	 * @param input
	 * @return
	 */
	public static String numToCapital(int input) {
		return String.valueOf((char) (input + 65));
	}

	public static String getResDomain(String url) {
		if (StringUtils.isEmpty(url))
			return null;
		return url.substring(0, url.lastIndexOf("/"));
	}

	public static void main(String[] args) {
		System.out.println(convertPhone("13876745210"));
		System.out.println(numToLowercase(0));
		System.out.println(getResDomain("http://yaotv-test.oss-cn-shenzhen.aliyuncs.com/shake_tv/auto/fb4bc11d0cf64ec6b3ed8a413c712d0d/index.html"));
	}
}
