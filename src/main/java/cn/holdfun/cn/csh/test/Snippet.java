/*
 * 版权所有 (C) 2014-2018 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2018-5-24，alex创建。 
 */
package cn.holdfun.cn.csh.test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.holdfun.cn.util.Md5Util;
import cn.holdfun.cn.util.UuidUtil;

public class Snippet {
	
	public static String buildSignature(Map<String, String> params, String secret) {
		List<String> list = new ArrayList<String>();
		Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
		try {
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				//System.out.println(entry.getKey() + ":" + entry.getValue() + "-=-=-=" + URLEncoder.encode(entry.getValue(), "utf-8"));
				list.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			System.out.println(str);
			sb.append(str).append("&");
		}
		sb.append("secret=" + secret);
		System.out.println("buildSignature =" + sb.toString());
		System.out.println("buildSignature md5=" + Md5Util.getMD5(sb.toString().getBytes()).toUpperCase());
		return Md5Util.getMD5(sb.toString().getBytes()).toUpperCase();
	
	}
	
	private static Map<String, String> getParams(Object req) {
		Map<String, String> map = new HashMap<String, String>();
		Class cls = req.getClass();
		Field[] fields = cls.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				if (f.get(req) != null)
					map.put(f.getName(), String.valueOf(f.get(req)));
			}
		} catch (Exception e) {
			return null;
		}
	
		return map;
	}
	
	public static String buildSign(String... params) {
		String sign = null;
		StringBuffer temp = new StringBuffer();
		for (String param : params) {
			temp.append(param);
		}
		// sign = Md5Util.getMD5(temp.toString().toLowerCase().getBytes());
		sign = Md5Util.getMD5(temp.toString().getBytes());

		return sign;
	}
	
	public static void main(String[] args) {
		String open_id = "f403c9a9-472b-4f97-a9cb-58c0b44eb227";
//		String open_id = "5986aae980698f06c9f90e434fa12b99";
		String nonce_str = UuidUtil.randomUUID();
		Map<String, String> params = new HashMap<String, String>();
		params.put("appID", "ICECGJLS-RF9A-5NY9-EREF-3VKPI6JOVW8J");
		//params.put("pay_user_id", "2222309");
		//params.put("amount", "1");
		//params.put("pano", "1039081f2de21cc7451bbb74cd3e8852");
		//params.put("out_transfer_no", UuidUtil.randomUUID());
		//params.put("trans_password", "123456");
		params.put("lishiUuid", "27a6838870cc4ea3bc52f24eaffe3f72");
		params.put("nonce_str", nonce_str);
		params.put("user_uuid", open_id);
		String secret = "TYHpsLtHeFXYRTekJbVv";
		String signature = buildSignature(params, secret);
		
		//System.out.println(buildSign("ICECLS0-FE8N-YNJI-L7JA-RPFVSEFH3BEA", "1528276264", "XcaYh8R5W4vGEzK7GDLm", "false"));
	}
}

