package cn.holdfun.cn.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie操作工具类
 * 
 * @author Alex
 * 
 */
public class CookieUtil {

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (null != cookieMap && cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		}
		return null;
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 删除cookie
	 * 
	 * @param response
	 * @param name
	 */
	public static void removeCookie(HttpServletResponse response, String name) {
		Cookie newCookie = new Cookie(name, null); // 假如要删除名称为username的Cookie
		newCookie.setMaxAge(0); // 立即删除型
		newCookie.setPath("/"); // 项目所有目录均有效，这句很关键，否则不敢保证删除
		response.addCookie(newCookie); // 重新写入，将覆盖之前的
	}

	/**
	 * 修改cookie的值
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param newValue
	 */
	public static void updateCookie(HttpServletRequest request, HttpServletResponse response, String name, String newValue) {
		Cookie cookie = getCookieByName(request, name);
		cookie.setValue(newValue);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length <= 0)
			return null;

		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie);
		}
		return cookieMap;
	}
}
