package cn.holdfun.cn.util;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * ip工具类
 * 
 * @author cfuzhao
 * 
 */
public class IpAddrUtil {

	/**
	 * 查询IP地址所属城市
	 * 
	 * @param ip
	 * @return
	 */
	public static String getCityByIp(String ip) {
		if (StringUtils.isEmpty(ip)) {
			return null;
		}
		String city = null;
		String reqUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
		String json = HttpUtil.sendHttpGetReqToServerByHighPerformance(reqUrl);
		JSONObject jsonObj = JSONObject.fromObject(json);
		if ((Integer) jsonObj.get("code") == 0) {
			city = (String) jsonObj.getJSONObject("data").get("city");
		}
		return city;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"code\":0,\"data\":{\"country\":\"\\u4e2d\\u56fd\",\"country_id\":\"CN\",\"area\":\"\\u534e\\u5317\",\"area_id\":\"100000\",\"region\":\"\\u5317\\u4eac\\u5e02\",\"region_id\":\"110000\",\"city\":\"\\u5317\\u4eac\\u5e02\",\"city_id\":\"110000\",\"county\":\"\",\"county_id\":\"-1\",\"isp\":\"\\u963f\\u91cc\\u4e91\",\"isp_id\":\"1000323\",\"ip\":\"182.92.185.131\"}}";
		JSONObject jsonObj = JSONObject.fromObject(json);

		System.out.println(jsonObj.get("code"));
		System.out.println(jsonObj.get("data"));
		System.out.println(jsonObj.getJSONObject("data").get("region"));

		System.out.println(getCityByIp("218.4.57.250"));
	}

}
