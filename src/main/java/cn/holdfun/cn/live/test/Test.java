package cn.holdfun.cn.live.test;


import java.util.Random;

import cn.holdfun.cn.util.HttpUtil;

public class Test {
	public static void main(String[] args) {
		/*long a = Long.valueOf(DateTimeUtil.getTime6String());
		System.out.println(a);
		System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
		int b = (int)a;
		System.out.println(b);
		
		System.out.println(a == b);*/
//		String domain = "http://zhibo.holdfun.cn";
		String domain = "http://testlive.holdfun.cn";
//		String domain = "http://localhost";
//		String roomId = "03ed4fd3c498412699620ef69cfdff2b";
		String roomId = "847021ff7b6749b0879744494a92d1b9";
		String enterId = "c97db9f68bc54b059a6d3cf1fdd368b6";
		//陈平
		String uu1 = "ee5c99abb06bc2d9bffecfa0347cd8d5";
		String uc1 = "0722c8e5c347b326dc1ea6bf9c3b486b";
		
		//海王子
		String uu2 = "8619bf3d142e855e938e99e69f7d6bb9";
		String uc2 = "ec91f168c815346bea4bd816970c0f33";
		
		
		String giftUuid1= "2284376d58d44760bfb23bc7ef244764";
		String giftUuid2= "6397918e84724e38b662a7546cde106b";
		
		Random r = new Random();
		while (true) {
			System.out.println(HttpUtil.sendHttpGetReqToServer(domain + "/lportal/api/gift/send?uu="+uu1+"&uc="+uc1+"&roomId="+roomId+"&giftId="+giftUuid1+"&num="+r.nextInt(10)));
			System.out.println(HttpUtil.sendHttpGetReqToServer(domain + "/lportal/api/gift/send?uu="+uu2+"&uc="+uc2+"&roomId="+roomId+"&giftId="+giftUuid1+"&num="+r.nextInt(10)));
			System.out.println(HttpUtil.sendHttpGetReqToServer(domain + "/lportal/api/comments/save?uu="+uu1+"&uc="+uc1+"&roomId="+roomId+"&con=Are%20you%20ok"+r.nextInt(100000000)));
			System.out.println(HttpUtil.sendHttpGetReqToServer(domain + "/lportal/api/comments/save?uu="+uu2+"&uc="+uc2+"&roomId="+roomId+"&con=Are%20you%20ok"+r.nextInt(100000000)));
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			System.out.println(DateTimeUtil.getDateTimeString() + "====" + HttpUtil.sendHttpGetReqToServer(domain + "/lportal/api/gift/heartbeat?roomId="+roomId+"&maxid=0&callback=LiveGiftHeartbeatCallback"));
//			System.out.println(DateTimeUtil.getDateTimeString() + "====" + HttpUtil.sendHttpGetReqToServer(domain + "/lportal/api/gift/heartbeat?roomId="+roomId+"&maxid=0"));
		}
		/*System.out.println(DateTimeUtil.nowDate().getTime());
		System.out.println(DateTimeUtil.nowDate().getTime() / 1000);
		System.out.println(Integer.MAX_VALUE);*/
	}
}
