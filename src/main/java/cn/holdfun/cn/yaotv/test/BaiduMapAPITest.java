package cn.holdfun.cn.yaotv.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import net.sf.json.JSONObject;
import cn.holdfun.cn.util.HttpUtil;
import cn.holdfun.cn.util.UnicodeUtil;

public class BaiduMapAPITest {
	private static final String ak = "xZiY76nNEvjGjgIxFAVqtAAD";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String aa = "http://api.map.baidu.com/geocoder/v2/?ak=xZiY76nNEvjGjgIxFAVqtAAD&callback=renderReverse&location=4288882.83,13194582.7&output=json&pois=1";
		System.out.println(HttpUtil.sendHttpGetReqToServerByHighPerformance(aa));
		
		// TODO Auto-generated method stub
		String reqUrl = "http://api.map.baidu.com/location/ip?ak=xZiY76nNEvjGjgIxFAVqtAAD&ip=223.104.2.233";
		String response = HttpUtil.sendHttpGetReqToServerByCustomCookie(reqUrl);
		System.out.println(response);
		
		try {
			System.out.println(URLDecoder.decode(response, StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(decodeUnicode(response));
		
		String finalRes = decodeUnicode(response);
		System.out.println(JSONObject.fromObject(finalRes).getJSONObject("content").getJSONObject("address_detail").getString("city"));
		
		
		response = UnicodeUtil.decodeUnicode(response);
		JSONObject json = JSONObject.fromObject(response);
		if(null == json || !json.containsKey("status"))
			System.out.println(1);
		
		int status = json.getInt("status");
		if(0 != status)
			System.out.println(2);
		
		if(json.containsKey("content"))
		{
			JSONObject content = json.getJSONObject("content");
			if(content.containsKey("address_detail")){
				JSONObject address_detail = content.getJSONObject("address_detail");
				if(address_detail.containsKey("city_code"))
					System.out.println(address_detail.getString("city_code"));
			}
		}
		System.out.println(3);
	}
	
	
	 public static String decodeUnicode(String theString) {    
		  
	     char aChar;    
	  
	      int len = theString.length();    
	  
	     StringBuffer outBuffer = new StringBuffer(len);    
	  
	     for (int x = 0; x < len;) {    
	  
	      aChar = theString.charAt(x++);    
	  
	      if (aChar == '\\') {    
	  
	       aChar = theString.charAt(x++);    
	  
	       if (aChar == 'u') {    
	  
	        // Read the xxxx    
	  
	        int value = 0;    
	  
	        for (int i = 0; i < 4; i++) {    
	  
	         aChar = theString.charAt(x++);    
	  
	         switch (aChar) {    
	  
	         case '0':    
	  
	         case '1':    
	  
	         case '2':    
	  
	         case '3':    
	  
	        case '4':    
	  
	         case '5':    
	  
	          case '6':    
	           case '7':    
	           case '8':    
	           case '9':    
	            value = (value << 4) + aChar - '0';    
	            break;    
	           case 'a':    
	           case 'b':    
	           case 'c':    
	           case 'd':    
	           case 'e':    
	           case 'f':    
	            value = (value << 4) + 10 + aChar - 'a';    
	           break;    
	           case 'A':    
	           case 'B':    
	           case 'C':    
	           case 'D':    
	           case 'E':    
	           case 'F':    
	            value = (value << 4) + 10 + aChar - 'A';    
	            break;    
	           default:    
	            throw new IllegalArgumentException(    
	              "Malformed   \\uxxxx   encoding.");    
	           }    
	  
	         }    
	          outBuffer.append((char) value);    
	         } else {    
	          if (aChar == 't')    
	           aChar = '\t';    
	          else if (aChar == 'r')    
	           aChar = '\r';    
	  
	          else if (aChar == 'n')    
	  
	           aChar = '\n';    
	  
	          else if (aChar == 'f')    
	  
	           aChar = '\f';    
	  
	          outBuffer.append(aChar);    
	  
	         }    
	  
	        } else   
	  
	        outBuffer.append(aChar);    
	  
	       }    
	  
	       return outBuffer.toString();    
	  
	      }   

}
