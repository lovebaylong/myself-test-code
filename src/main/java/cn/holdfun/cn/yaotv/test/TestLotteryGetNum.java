package cn.holdfun.cn.yaotv.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import cn.holdfun.cn.mp.test.UuidUtil;
import cn.holdfun.cn.util.HttpUtil;

public class TestLotteryGetNum {
	
	public static void main(String[] args) {
		/*for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					send();
				}
			}).start();
		}*/
		
		List<Integer> ll = new ArrayList<Integer>();
		for(int i = 0; i<5;i++)
			ll.add(i);
		//dtos.subList(0, dtos.size() >= 10 ? 9 : dtos.size());
		List<Integer> la = ll.subList(0, ll.size() >= 10 ? 10 : ll.size());
		for(Integer i : la)
			System.out.println(i);
	}
	
	private static void send() {
		String uuid = UuidUtil.randomUUID();
		String openid = String.valueOf(RandomUtils.nextInt(999999));
		String scu = "0a7e7d7417cb41399bd7b8d4e33082b5";
		String reqUrl = "http://localhost/portal/api/lottery/share/grabnum?sud="+scu+"&openid="+openid+"&nickname="+uuid+"&headImageUrl="+uuid+".jpg";
		String result = HttpUtil.sendHttpGetReqToServer(reqUrl);
		System.out.println(result);
	}
}
