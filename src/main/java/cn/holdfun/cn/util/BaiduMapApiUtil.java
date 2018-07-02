/**
 * 
 */
package cn.holdfun.cn.util;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * 百度公共的API工具类
 * @author alex
 *
 */
public class BaiduMapApiUtil {
	//private static final Log logger = LogFactory.getLog(BaiduMapApiUtil.class);
	//IP定位 API是一个根据IP返回对应位置信息的http形式位置服务接口, 该接口是免费对外开放，您需先申请密钥（ak）才可使用。每个key支持100万次/天，超过限制不返回数据。
	private static final String BAIDU_MAP_API_LOCATION_IP_URL = "http://api.map.baidu.com/location/ip?ak=%s&ip=%s";
	
	/**
	 * 根据用户IP地址返回用户对应的城市名称
	 * @param ak：百度开发者访问应用（AK）
	 * @param ip：用户IP地址
	 * @return
	 */
	public static String locationIp4CityName(String ak, String ip){
		return locationIp(ak, ip, "city");
	}
	/**
	 * 根据ip返回用户所在省份
	 * @param ak
	 * @param ip
	 * @return
	 */
	public static String locationIp4ProvinceName(String ak, String ip){
		return locationIp(ak,ip,"province");
	}
	
	/**
	 * 根据用户IP地址返回用户对应的城市代码
	 * @param ak：百度开发者访问应用（AK）
	 * @param ip：用户IP地址
	 * @return
	 */
	public static String locationIp4CityCode(String ak, String ip){
		return locationIp(ak, ip, "city_code");
	}
	
	private static String locationIp(String ak, String ip, String key){
		if(StringUtils.isEmpty(ak) || StringUtils.isEmpty(ip))
			return null;
		
		String reqUrl = String.format(BAIDU_MAP_API_LOCATION_IP_URL, ak, ip);
		String response = HttpUtil.sendHttpGetReqToServerByHighPerformanceAndTimeout(reqUrl);
//		String response = HttpUtil.sendHttpGetReqToServerByCustomCookie(reqUrl);
//		System.out.println("location user's ip, response = " + UnicodeUtil.decodeUnicode(response));
		if(StringUtils.isEmpty(response))
			return null;
		
		//转换里面的unicode汉字
		response = UnicodeUtil.decodeUnicode(response);
		JSONObject json = JSONObject.fromObject(response);
		if(null == json || !json.containsKey("status"))
			return null;
		
		int status = json.getInt("status");
		if(0 != status)
			return null;
		
		if(json.containsKey("content"))
		{
			JSONObject content = json.getJSONObject("content");
			if(content.containsKey("address_detail")){
				JSONObject address_detail = content.getJSONObject("address_detail");
				if(address_detail.containsKey(key))
					return address_detail.getString(key);
			}
		}
		return null;
	}
	
	
	  public static void main(String[] args) {
	    	String ak = "xZiY76nNEvjGjgIxFAVqtAAD";
	 		String ip = "113.102.162.189";
			System.out.println(BaiduMapApiUtil.locationIp4ProvinceName(ak, ip));
			System.out.println(BaiduMapApiUtil.locationIp4CityCode(ak, ip));
	  }
}
