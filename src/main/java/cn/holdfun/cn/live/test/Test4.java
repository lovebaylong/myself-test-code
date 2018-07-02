package cn.holdfun.cn.live.test;
/**
 * 
 */


import org.apache.commons.codec.digest.DigestUtils;

import cn.holdfun.cn.mp.test.UuidUtil;
import cn.holdfun.cn.util.HttpUtil;

/**
 * @author alex
 *
 */
public class Test4 {
	private static String oneShenzhenUserAuthUrl = "http://api.scms.sztv.com.cn:3000/api/member/auth?userId=%s&nonce_str=%s&appId=%s&sign=%s";
	private static String oneShenzhenUserAuthAppid = "e9cfb14825be0d2c4aee22d1a78bc81e";
	private static String oneShenzhenUserAuthAppkey = "aba7126825a721d3ea8e6fa7b760b98e";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int userId = 1;
		String nonce_str = UuidUtil.randomUUID();
		String sign = sign(oneShenzhenUserAuthAppid, oneShenzhenUserAuthAppkey, nonce_str, userId);
		System.out.println("请求串：" + String.format(oneShenzhenUserAuthUrl, userId, nonce_str, oneShenzhenUserAuthAppid, sign));
		//String userAuthResp = HttpUtil.sendHttpGetReqToServerByHighPerformance(String.format(oneShenzhenUserAuthUrl, userId, nonce_str, oneShenzhenUserAuthAppid, sign));
		//String userAuthResp = HttpUtil.sendHttpGetReqUrlToServer(String.format(oneShenzhenUserAuthUrl, userId, nonce_str, oneShenzhenUserAuthAppid, sign));
		System.out.println("响应串：" + HttpUtil.sendHttpGetReqUrlToServer(String.format(oneShenzhenUserAuthUrl, userId, nonce_str, oneShenzhenUserAuthAppid, sign)));
		System.out.println("响应串：" + HttpUtil.sendHttpGetReqToServer(String.format(oneShenzhenUserAuthUrl, userId, nonce_str, oneShenzhenUserAuthAppid, sign)));
		System.out.println("响应串：" + HttpUtil.sendHttpGetReqToServerByHighPerformanceAndTimeout(String.format(oneShenzhenUserAuthUrl, userId, nonce_str, oneShenzhenUserAuthAppid, sign)));
	}

	private static String sign(String appid, String appkey, String nonce_str, int userId) {
		String SignTemp = "appid=" + appid + "&nonce_str=" + nonce_str + "&userId=" + userId + "&key=" + appkey;
		System.out.println("加密前：" + SignTemp.toLowerCase());
		//System.out.println(Md5Util.getMD5(SignTemp.toLowerCase().getBytes()).toLowerCase());
		System.out.println("加密后：" + DigestUtils.md5Hex(SignTemp.toLowerCase().getBytes()).toLowerCase());;
		//return Md5Util.getMD5(SignTemp.toLowerCase().getBytes()).toLowerCase();
		return DigestUtils.md5Hex(SignTemp.toLowerCase().getBytes()).toLowerCase();
	}
}
