package cn.holdfun.cn.live.test;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.holdfun.cn.util.DateTimeUtil;
import cn.holdfun.cn.util.HttpUtil;

public class Test2 {
	private final static ScheduledExecutorService SYNC_CACHE_THREE_SEC_EXEC = Executors.newSingleThreadScheduledExecutor();
	private static String HISTORY_RESP = null;
	private static String PRE_TIME = DateTimeUtil.getDateTimeString();
	private static long PRE_BREAK_TIME = 0l;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * String maxId = "0"; while(true){ System.out.println(
		 * "http://zhibo.holdfun.cn/lportal/api/comments/barrage?repTuid=4530405a438b4d1fb7ec7b388e86cf9f&maxId="
		 * +maxId); String ret = HttpUtil.sendHttpGetReqToServer(
		 * "http://localhost/lportal/api/comments/barrage?repTuid=4530405a438b4d1fb7ec7b388e86cf9f&maxId="
		 * +maxId); System.out.println(ret); System.out.println(); try {
		 * if(StringUtils.isNotEmpty(ret) && JSONUtils.mayBeJSON(ret.trim())){
		 * JSONObject json = JSONObject.fromObject(ret);
		 * if(json.containsKey("maxid")) maxId = json.getString("maxid"); }
		 * Thread.sleep(3000); } catch (Exception e) { e.printStackTrace(); } }
		 */

		/*
		 * {
		 * 
		 * }
		 */

		/*
		 * String content = ""; String enterId =
		 * "5c53d6469cc444c085d894d754a19aea"; int height = 667; String imageurl
		 * =
		 * "http://statics.holdfun.cn/fandom/resources/images//2016/09/07/9ec81cc635554de6b2a1fdfeee170907.jpg"
		 * ; String phone = "13418822097"; String sign =
		 * "49a1b19c3ff9176ed2822c9318864ad7"; String title = "Fyh "; int width
		 * = 375;
		 * 
		 * String value = Constants.SIGN_CODE_KEY + "enterId=" + enterId +
		 * "&phone=" + phone + "&title=" + title + "&content=" + content +
		 * "&imageurl=" + imageurl + "&width=" + width + "&height=" + height;
		 * System.out.println(DigestUtils.md5Hex(value));
		 */

		

		SYNC_CACHE_THREE_SEC_EXEC.scheduleAtFixedRate(new Runnable() {
			public void run() {
				synchronous3SecondsTask();
			}
		}, 0L, 4L, TimeUnit.SECONDS);
		/*String starttime = DateTimeUtil.getDateTimeString();
		System.out.println((DateTimeUtil.toDate(starttime).getTime() / 1000L));
		System.out.println((int) (DateTimeUtil.toDate(starttime).getTime() / 1000L));*/

	}

	private static void synchronous3SecondsTask() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("开始确认直播流状态-----------------------------");
		try {
			String currentTime = DateTimeUtil.getDateTimeString();
			long current = DateTimeUtil.nowDate().getTime();
//			String url = "http://lhplay.holdfun.cn/96f70c20dbcc4d69a2c611bc8e50a465/N0z3rx.m3u8";
			String url = "http://lhmvod.holdfun.cn/96f70c20dbcc4d69a2c611bc8e50a465/N0z3rx.m3u8?start_time=1484214951&end_time=1484215851";
			String resp = HttpUtil.sendHttpGetReqToServer(url);
			System.out.println("直播流轮询结果响应，轮询地址："+url+"， 响应结果： ");
			System.out.println(resp);
			boolean hasNotLiving = false;
			//当前流与上一次的不同并且不是404，表示正在直播中，否则表示断流了，返回的是缓存流
			if(!resp.equalsIgnoreCase(HISTORY_RESP) && (resp.indexOf("404 Not Found") < 0) && (resp.indexOf("502 Bad Gateway") < 0)){
				System.out.println("正在直播中。。。");
				HISTORY_RESP = resp;
				PRE_BREAK_TIME = 0L;
			}
			else {
				System.out.println("直播断流，当前时间="+currentTime);
				hasNotLiving = true;
				if(PRE_BREAK_TIME == 0L)
					PRE_BREAK_TIME = DateTimeUtil.nowDate().getTime();
			}
			
			if(hasNotLiving){
				System.out.println("直播流不存在，主播未直播！时间段：" + PRE_TIME + "~" + currentTime);
				
				//判断最近一次断流是否超过30分钟断流，如果是就不需要再轮询了
				if((current - PRE_BREAK_TIME) / (1000*60) > 2)
					System.out.println("直播流不存在，主播断流达到2分钟，不再并入同一个录播记录");
			}
			PRE_TIME = currentTime;
		} catch (Exception e) {
			e.printStackTrace();
			//logger.info("SynchronousData2CacheStarter occur exception, e = " + e);
		} catch (Error e) {
//			logger.info("SynchronousData2CacheStarter occur exception, e = " + e);
		} catch (Throwable e) {
//			logger.info("SynchronousData2CacheStarter occur exception, e = " + e);
		}

		System.out.println("结束确认直播流状态*************************************");
	}
}
