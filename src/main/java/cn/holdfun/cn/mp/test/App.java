package cn.holdfun.cn.mp.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Hello world!
 *
 */
public class App 
{
	//private static final String appId = "wx9e3b6ed9fe2bf4ca";
	//private static final String appSecret = "f802a1d76398c59341ef53c53591db48";
	//private static final String accessToken = "OSqZOd_8kPKMeWmabSnAEZRtVeK65OoZhZPhZeui6dS406J8NWW6nZskyJYSfcZW8HPVO3SnASPj4s0uDQp5i2on8-v35rEQCVa4vCoUvcs2Kv2jUxk65dHnPVV03eJyFVLcAFAIYO";
	
	//xzq.service@holdfun.cn
	private static final String appId = "wxa443e32d84844c0b";
//	private static final String appId = "wxc4594483f2548a4f";
	private static final String appSecret = "860851433a1e185d9009001d727af45f";
//	private static final String appSecret = "c0f3ac688305595939a2bd80bb838a1c";
	private static final String accessToken = "X6O3Fi-PsnrCdAHC4P5R_dDocWc_tEx77C4R_0t48YVsbIfr9wD-BgcFozFbBK_zHI01LXDebf59ElOh5oMyDBHxN_cOJ2CILjxhTZ0Ybh3QNC48aRVyLb-shJYimJfyVCCaAHAGNN";
	
	//图文消息的mediaid
	//private static final String mpnews_media_id = "q6kR-1dlwgXvxj3a6ooyqBYyLaOwVPNKYuQumjhpwf0";
	private static final String mpnews_media_id = "q6kR-1dlwgXvxj3a6ooyqG7pqwxK81RvAG3mEBFJVjw";
	
	
	private static final String WEI_GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";// 获取微信JS-SDK里面的jsapi_ticket
	
	//新增永久图文素材
	private static final String MP_MATERIAL_ADD_NEWS_URL ="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s&type=TYPE";
	//获取素材列表
	private static final String MP_MATERIAL_BATCH_GET_URL ="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
	//Push图文消息
	private static final String MP_MASS_SEND_ALL_URL ="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";
	public static final String WEI_XIN_MEDIA_DOWNLOAD_API_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
	
	public static final String WEI_XIN_CLEAR_QUOTA_API_URL ="https://api.weixin.qq.com/cgi-bin/clear_quota?access_token=%s";
	
	public static final String WEI_XIN_USER_INFO_API_URL ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
    public static void main( String[] args )
    {
    	getAccessToken();
    	//batchgetMaterial();
    	try {
//    		addMaterialEver();
        	//addMaterialTemp();
//    		getAccessToken();
//    		getUserInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	addMaterial();
    	
//    	sendAll();
    	
    	//downloadTempMaterial();
//    	clearQuota();
    	
    }
    
    private static void getUserInfo(){
    	String resp =  HttpUtil.sendHttpGetReqToServer(String.format(WEI_XIN_USER_INFO_API_URL, accessToken, "oBpcKwp9vGsb1_3wWbSsR2vvH37Q"));
    	System.out.println(resp);
    }
    
    private static void clearQuota(){
    	JSONObject j = new JSONObject();
    	j.put("appid", "wx71cce586e03f2423");
    	String resp = HttpUtil.sendHttpPostReqToServerByReqbody(String.format(WEI_XIN_CLEAR_QUOTA_API_URL, accessToken), j.toString(), "");
    	System.out.println("---- clearQuota, resp = " + resp);
    }
    
    private static void getAccessToken(){
    	String resp = HttpUtil.sendHttpGetReqToServer(String.format(WEI_GET_ACCESSTOKEN_URL, appId, appSecret));
    	System.out.println("---- get accessToken, appid = " + appId + ", resp = " + resp);
    }
    
    private static void downloadTempMaterial(){
    	byte[] datas = HttpUtil.sendHttpGetReqToServerAndGetDataBytes(String.format(WEI_XIN_MEDIA_DOWNLOAD_API_URL,
				accessToken, "8c002GfNARdyOGEOlONeSpxuxv7JvRU4GjxVpJbfcqvQcouNvTGkcnhereYzbteo"));
		if (null == datas || datas.length < 100){
			System.out.println(1);
			return;
		}
		
InputStream is = new  ByteArrayInputStream(datas);
		
		try {
			Thumbnails.of(is).useExifOrientation(false).useOriginalFormat().scale(1.0f).toFile("D://8c002GfNARdyOGEOlONeSpxuxv7JvRU4GjxVpJbfcqvQcouNvTGkcnhereYzbteo.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void batchgetMaterial(){
    	String template = "{\"type\":\"image\",\"offset\":0,\"count\":10}";
    	String resp = HttpUtil.sendHttpPostReqToServerByReqbody(String.format(MP_MATERIAL_BATCH_GET_URL, accessToken), template, null);
    	System.out.println(resp);
    }
    
    private static void addMaterial(){
//    	String THUMB_MEDIA_ID = "q6kR-1dlwgXvxj3a6ooyqIhXLQFObdPN6w8BOxRZX5U";
    	String THUMB_MEDIA_ID = "q6kR-1dlwgXvxj3a6ooyqLsXlQhFftkbf8TEFfgdvb8";
    	
    	JSONObject json = new JSONObject();
    	JSONArray ja = new JSONArray();
    	JSONObject j = new JSONObject();
    	j.put("title", "还是很有把握的");
    	j.put("thumb_media_id", THUMB_MEDIA_ID);
    	j.put("author", "笑神");
    	j.put("digest", "这就是个笑话");
    	j.put("show_cover_pic", 0);
    	j.put("content", "儿子中考考试考差了，被老婆骂了一顿。我去安慰儿子：“你要努力学习，以后一定要超越爸爸。”儿子愣了一下，弱弱来了一句：“别的我不敢保证。但是，以后找个比你好的老婆还是很有把握的。”‍‍‍‍");
    	j.put("content_source_url", "www.qq.com");
    	ja.add(j);
    	json.put("articles", ja);
    	//String THUMB_MEDIA_ID = "\"IpIRHH0qZTzbvCsqDR-KlLvJm9s4kodihrnN4bHd4K5tzKGh-MrXtksOSrpuvL5t\"";
    	
    	//String template = "{\"articles\": [{\"title\": \"测试标题\",\"thumb_media_id\": \"q6kR-1dlwgXvxj3a6ooyqIhXLQFObdPN6w8BOxRZX5U\",\"author\": \"AUTHOR\",\"digest\": 测试摘要,\"show_cover_pic\": 0,\"content\": \"abc\",\"content_source_url\": \"http://www.holdfun.cn\"}]}";
    	String template = json.toString();
    	String resp = HttpUtil.sendHttpPostReqToServerByReqbody(String.format(MP_MATERIAL_ADD_NEWS_URL, accessToken), template, null);
    	System.out.println(resp);
    }
    
    private static void addMaterialEver() throws Exception {  
    	String type = "thumb";
        try {  
        	File file = new File("d://img/page01.jpg");
            System.out.println("开始上传"+type+"永久素材---------------------");   
            //上传素材    
            //新增其他类型永久素材
            String path="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+accessToken+"&type="+type;  
            //新增临时素材
            //String path="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type="+type;  
            String result=connectHttpsByPost(path, file);  
            result=result.replaceAll("[\\\\]", "");  
            System.out.println("result:"+result);      
            /*JSONObject resultJSON=JSONObject.fromObject(result);  
            if(resultJSON!=null){  
                if(resultJSON.get("thumb_media_id")!=null){  
                    System.out.println("上传"+type+"永久素材成功==" + resultJSON.toString());  
                }else{  
                    System.out.println("上传"+type+"永久素材失败");  
                }  
            }  */
        } catch (Exception e) {  
            System.out.println(e);  
            throw e;  
        }finally{  
            System.out.println("结束上传"+type+"永久素材---------------------");   
        }  
    } 
    
    private static void addMaterialTemp() throws Exception {  
    	String type = "image";
    	try {  
    		File file = new File("d://img/1.jpg");
    		System.out.println("开始上传"+type+"临时素材---------------------");   
    		//上传素材    
    		//新增其他类型永久素材
    		String path="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type="+type;  
    		//新增临时素材
    		//String path="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type="+type;  
    		String result=connectHttpsByPost(path, file);  
    		result=result.replaceAll("[\\\\]", "");  
    		System.out.println("result:"+result);      
    		/*JSONObject resultJSON=JSONObject.fromObject(result);  
            if(resultJSON!=null){  
                if(resultJSON.get("thumb_media_id")!=null){  
                    System.out.println("上传"+type+"永久素材成功==" + resultJSON.toString());  
                }else{  
                    System.out.println("上传"+type+"永久素材失败");  
                }  
            }  */
    	} catch (Exception e) {  
    		System.out.println(e);  
    		throw e;  
    	}finally{  
    		System.out.println("结束上传"+type+"永久素材---------------------");   
    	}  
    } 
    
    private static void sendAll(){
    	/**
    	 * {
			   "filter":{
			      "is_to_all":false,
			      "group_id":2
			   },
			   "mpnews":{
			      "media_id":"123dsdajkasd231jhksad"
			   },
			    "msgtype":"mpnews"
			}
    	 */
    	//{"errcode":0,"errmsg":"send job submission success","msg_id":1000000001,"msg_data_id":2247483668}
    	JSONObject json = new JSONObject();
    	JSONObject fj = new JSONObject();
    	fj.put("is_to_all", true);
    	json.put("filter", fj);
    	
    	JSONObject mj = new JSONObject();
    	mj.put("media_id", mpnews_media_id);
    	json.put("mpnews", mj);
    	
    	json.put("msgtype", "mpnews");
    	String resp = HttpUtil.sendHttpPostReqToServerByReqbody(String.format(MP_MASS_SEND_ALL_URL, accessToken), json.toString(), null);
    	System.out.println(resp);
    }
    
    
    private static String connectHttpsByPost(String path, File file) throws Exception{
    	URL url = new URL(path);
    	URLConnection con = url.openConnection();
		String result =null;
		con.setDoInput(true);

		con.setDoOutput(true);

		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息

		con.setRequestProperty("Connection", "Keep-Alive");

		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界

		String BOUNDARY = "----------" + System.currentTimeMillis();

		con.setRequestProperty("Content-Type",
				"multipart/form-data; boundary="

				+ BOUNDARY);

		// 请求正文信息

		// 第一部分：

		StringBuilder sb = new StringBuilder();

		sb.append("--"); // 必须多两道线

		sb.append(BOUNDARY);

		sb.append("\r\n");

		sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""

				+ file.getName() + "\"\r\n");

		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流

		OutputStream out = new DataOutputStream(con.getOutputStream());

		// 输出表头

		out.write(head);

		// 文件正文部分

		// 把文件已流文件的方式 推入到url中

		DataInputStream in = new DataInputStream(new FileInputStream(file));

		int bytes = 0;

		byte[] bufferOut = new byte[1024];

		while ((bytes = in.read(bufferOut)) != -1) {

			out.write(bufferOut, 0, bytes);

		}

		in.close();

		// 结尾部分

		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();

		out.close();

		StringBuffer buffer = new StringBuffer();

		BufferedReader reader = null;

		try {

			// 定义BufferedReader输入流来读取URL的响应

			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String line = null;

			while ((line = reader.readLine()) != null) {

				buffer.append(line);

			}

			if (result == null) {

				result = buffer.toString();

			}

		} catch (IOException e) {

			System.out.println("发送POST请求出现异常！" + e);

			e.printStackTrace();

			throw new IOException("数据读取异常");

		} finally {

			if (reader != null) {

				reader.close();

			}

		}
		return result;
    }
}
