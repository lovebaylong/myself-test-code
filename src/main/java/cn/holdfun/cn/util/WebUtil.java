package cn.holdfun.cn.util;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.WebUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * web相关操作工具类
 * 
 * @author Alex
 * 
 */
public class WebUtil {

	private static Log log = LogFactory.getLog(WebUtils.class);
	private static final String ANALYZE_USERAGENT_API_URL = "http://www.useragentstring.com/?uas=%s&getJSON=all";
	

	/**
	 * 获取请求客户端的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = getWholeIpAddr(request);
		if (StringUtils.isEmpty(ip))
			return null;

		if (ip.indexOf(",") > 0) {
			String[] ips = ip.split(",");
			for (String i : ips) {
				if (StringUtils.isEmpty(i))
					continue;
				i = i.trim();
				if (isVaildIp(i))
					return i;
			}
			ip = ips[0].trim();
		}
		return ip.trim();
	}
	
	/**
	 * 获取请求客户端的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getWholeIpAddr(HttpServletRequest request) {
		if (null == request)
			return null;
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 是否是有效的IP地址
	 * @param ip
	 * @return
	 */
	public static boolean isVaildIp(String ip){
		/*
		 * 	IPv4专用地址如下：
			IP等级 IP位置
			Class A 10.0.0.0-10.255.255.255
			默认子网掩码:255.0.0.0
			Class B 172.16.0.0-172.31.255.255
			默认子网掩码:255.255.0.0
			Class C 192.168.0.0-192.168.255.255
			默认子网掩码:255.255.255.0
		 */
		if(StringUtils.isEmpty(ip))
			return false;
		return !(ip.startsWith("10.") || ip.startsWith("172.") || ip.startsWith("192."));
	}
	
	/**
	 * 根据userAgent解析出来用户终端的类型，1安卓；2IOS；3微软 -1表示非法终端
	 * 
	 * @param userAgent
	 * @return
	 */
	/*public static int getMobileTerminalType(String userAgent) {
		if (StringUtils.isEmpty(userAgent))
			return -1;
		if (!userAgent.contains("MicroMessenger") && !userAgent.contains("Mobile")) 
			return -1;
		
		if (userAgent.contains("Android"))
			return Constants.TERMINAL_TYPE_ANDROID;
		else if (userAgent.contains("Mac"))
			return Constants.TERMINAL_TYPE_IOS;
		else if (userAgent.contains("Windows"))
			return Constants.TERMINAL_TYPE_WINDOWS;
		else
			return -1;
	}*/
	
	/**
	 * 解析用户的UserAgent，you will get a text file with a JSON object like
	 * {"agent_type":"Browser","agent_name":"Opera","agent_version":"9.70",
	 * "os_type":"Linux","os_name":"Linux"....
	 * 
	 * @param userAgent
	 * @return
	 */
	public static JSONObject analyzeUserAgent(String userAgent) {
		if(StringUtils.isEmpty(userAgent))
			return null;
		try {
			String response = HttpUtil.sendHttpGetReqToServerByHighPerformanceAndTimeout(String.format(ANALYZE_USERAGENT_API_URL, URLEncoder.encode(userAgent, "UTF-8")));
			if(StringUtils.isEmpty(response))
				return null;
			return JSONObject.fromObject(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向请求响应写异步的json格式消息
	 * 
	 * @param response
	 * @param jsonString
	 */
	public static void writeJsonAjaxResponse(HttpServletResponse response, String jsonString) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF8");
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			writer.write(jsonString);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
	
	public static void main(String[] args) {
		/*System.out.println(isVaildIp("10.0.0.0"));
		System.out.println(isVaildIp("172.16.0.0"));
		System.out.println(isVaildIp("192.168.0.0"));
		
		System.out.println(isVaildIp("110.0.0.0"));*/
		//System.out.println(getIpAddr(null));
		System.out.println(analyzeUserAgent("Mozilla/5.0 (Linux; Android 5.1.1; MI NOTE Pro Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036548 Safari/537.36 MicroMessenger/6.3.22.821 NetType/ctnet Language/zh_CN"));
	}

	public static void logRequestInformation(HttpServletRequest request) {
		log.info("scheme = " + request.getScheme() + "");
		log.info("serverName = " + request.getServerName() + "");
		log.info("serverPort = " + request.getServerPort() + "");
		log.info("contextPath = " + request.getContextPath() + "");
		log.info("servletPath = " + request.getServletPath() + "");
		log.info("queryString = " + request.getQueryString() + "");
		log.info("requestURI = " + request.getRequestURI() + "");
		log.info("info = " + request.getPathInfo() + "");
		log.info("request= " + request.toString() + "");
	}
}
