package cn.holdfun.cn.yaotv.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import cn.holdfun.cn.util.HttpUtil;

public class SignTest {
	public static void main(String[] args) {
		
		String as = testGetAccessToken();
		System.out.println("accessToken = " + as);
		

		String jsapi_ticket = testGetJSAPI(as);
		System.out.println("jsapi_ticket = " + jsapi_ticket);
		
		// 注意 URL 一定要动态获取，不能 hardcode
		String url = "http://yao.holdfun.cn/xzq/index.html";
		Map<String, String> ret = sign(jsapi_ticket, url);
		for (Map.Entry entry : ret.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	};

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	



	static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	static final String GET_JS_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
	private static final String APPID = "wx9097d74006e67df3";
	private static final String SECRET = "657d79b91f65a0450ae02adaeb1712f5";
	/**
	 * 获取AccessToken
	 * 
	 * @return
	 */
	private static String testGetAccessToken() {
		String responseContent = HttpUtil.sendHttpGetReqToServer(String.format(GET_ACCESS_TOKEN_URL, APPID, SECRET));
		if (StringUtils.isEmpty(responseContent))
			return null;
		JSONObject responseJson = JSONObject.fromObject(responseContent);
		if (null == responseJson)
			return null;

		return responseJson.getString("access_token");
	}
	
	private static String testGetJSAPI(String as) {
		String responseContent = HttpUtil.sendHttpGetReqToServer(String.format(GET_JS_TICKET_URL, as));
		if (StringUtils.isEmpty(responseContent))
			return null;
		JSONObject responseJson = JSONObject.fromObject(responseContent);
		if (null == responseJson)
			return null;

		return responseJson.getString("ticket");
	}
}
