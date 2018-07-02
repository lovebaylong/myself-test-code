package cn.holdfun.cn.util;

public class WeiXinCookieUtil {
	/**
	 * 返回微信cookie里面name对应的值
	 * 
	 * @param name：微信cookie里面对应的名称
	 * @param userProfileCookie：微信cookie的值
	 * @return
	 */
	public static String analysis(String name, String userProfileCookie) {
		String c_value = " " + userProfileCookie;
		int c_start = c_value.indexOf(" " + name + "="), c_end;
		if (c_start == -1) {
			c_value = null;
		} else {
			c_start = c_value.indexOf("=", c_start) + 1;
			c_end = c_value.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = c_value.length();
			}
			c_value = EscapeUtil.unescape(c_value.substring(c_start, c_end));
		}
		return c_value;
	}
}
