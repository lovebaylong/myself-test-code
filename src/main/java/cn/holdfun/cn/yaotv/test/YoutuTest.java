package cn.holdfun.cn.yaotv.test;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.holdfun.cn.util.Base64Util;

public class YoutuTest {
	private static final Log log = LogFactory.getLog(YoutuTest.class);
	private static String interUrl = "http://api.youtu.qq.com/youtu/api/detectface";
	private static int AppID = 10008631;
	private static String qq = "154052684";
	private static String SecretID = "AKIDKj4GG4rmJqFoET8wSvDLTXdbZO2d5fQf";
	private static String SecretKey = "xmS7cw64MVPBrIVjlBnJkWioqe8GVYfq";   
	private static final String MAC_NAME = "HmacSHA1";    
    private static final String ENCODING = "UTF-8";    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JSONObject json = new JSONObject();
		json.put("app_id", AppID);
		json.put("url", "http://open.youtu.qq.com/content/img/product/face/face_09.jpg?v=2.0");
		json.put("mode", 0);
		
		//String resp = HttpUtil.sendHttpPostReqToServerByReqbody(interUrl, json.toString(), "text/json");
		System.out.println(sign());
		
		/**
		 * 参数名	值	描述
			Host	api.youtu.qq.com	优图云服务器域名
			Content-Length	包体总长度	整个请求包体内容的总长度，单位：字节（Byte）
			Content-Type	text/json	请求格式为json
			Authorization	鉴权签名	用于鉴权的签名，具体生成方式详见鉴权服务技术方案
		 */
		/*Map<String, String> headParams = new HashMap<String, String>();
		headParams.put("Host", "api.youtu.qq.com");
		//headParams.put("Content-Length", Integer.toString(json.toString().getBytes().length));
		headParams.put("Content-Type", "text/json");
		headParams.put("Authorization", sign());
		 
		String resp = sendHttpPostReqToServerByReqbody(interUrl, headParams, json.toString(), "text/json");
		System.out.println(resp);*/

	}

	
	 
	 private static String sign(){
		 /*
		  * u为开发者创建应用时的QQ号

			a为用户的AppID
			
			k为用户的SecretID
			
			t为当前时间戳，是一个符合UNIX Epoch时间戳规范的数值，单位为秒
			
			e为此签名的凭证有效期，是一个符合UNIX Epoch时间戳规范的数值，单位为秒, e应大于t, 生成的签名在 t 到 e 的时间内 都是有效的. 如果是0, 则生成的签名只有再t的时刻是有效的.
			
			r为随机串，无符号10进制整数，用户需自行生成，最长10位。
			
			f为空
			
			拼接有效签名串的结果,下文称之为orignal
		  */
		// long t = DateTimeUtil.nowDate().getTime()/1000;
		 long t = 1476435476;
		 System.out.println(t);
		 //long e = t+7200;
		 long e =1476442676;
		 System.out.println(e);
		String  orignal = "u="+qq+"&a="+AppID+"&k="+SecretID+"&e="+e+"&t="+t+"&r=270494647&f=";
		
		//根据签名方法Sign= Base64(HMAC-SHA1(SecretKey, orignal) + original)
		try {
			String signStr = Base64Util.base64EncodeByte(HmacSHA1Encrypt(orignal, SecretKey));
			return signStr;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	 }
	 
	 /**  
	     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名  
	     * @param encryptText 被签名的字符串  
	     * @param encryptKey  密钥  
	     * @return  
	     * @throws Exception  
	     */    
	    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception     
	    {           
	        byte[] data=encryptKey.getBytes(ENCODING);  
	        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称  
	        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);   
	        //生成一个指定 Mac 算法 的 Mac 对象  
	        Mac mac = Mac.getInstance(MAC_NAME);   
	        //用给定密钥初始化 Mac 对象  
	        mac.init(secretKey);    
	          
	        byte[] text = encryptText.getBytes(ENCODING);    
	        //完成 Mac 操作   
	        return mac.doFinal(text);    
	    }  
	    
	    public static String sendHttpPostReqToServerByReqbody(String httpServerUrl,Map<String, String> headParams, String reqBody, String contentType) {
			if (StringUtils.isEmpty(httpServerUrl))
				return null;
			if (StringUtils.isEmpty(contentType))
				contentType = "application/x-www-form-urlencoded";
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String result = null;
			String status = null;
			try {
				HttpPost httpPost = new HttpPost(httpServerUrl);
				if(null != headParams && !headParams.isEmpty()){
					for(Map.Entry<String, String> entry : headParams.entrySet()){
						httpPost.addHeader(entry.getKey(), entry.getValue());
					}
				}
				if (StringUtils.isNotEmpty(reqBody)) {
					StringEntity reqBodyEntity = new StringEntity(reqBody, StandardCharsets.UTF_8);
					reqBodyEntity.setContentType(contentType);
					httpPost.setEntity(reqBodyEntity);
				}
				CloseableHttpResponse response1 = httpclient.execute(httpPost);
				try {
					if (null != response1) {
						if (null != response1.getStatusLine())
							status = response1.getStatusLine().toString();
						if (null != response1.getEntity())
							result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
					}
				} catch (ParseException e) {
					log.error("向Http服务端发送请求时发生异常，原因：", e);
				} finally {
					if (response1 != null)
						response1.close();
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (httpclient != null) {
					try {
						httpclient.close();
					} catch (Exception e) {
						log.error("关闭httpclient时发生异常，原因：", e);
					}
				}
			}
			log.info(String.format("向Http服务端发送请求，请求地址：%s，请求报文：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, reqBody, status,
					result));
			return result;
		}
}
