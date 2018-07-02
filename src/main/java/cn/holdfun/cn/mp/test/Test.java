package cn.holdfun.cn.mp.test;

import cn.holdfun.cn.util.HttpUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://s.weibo.com/weibo/MyRadio?topnav=1&wvr=6&b=1";
		String res = HttpUtil.sendHttpGetReqToServer(url);
		System.out.println(res);

	}

}
