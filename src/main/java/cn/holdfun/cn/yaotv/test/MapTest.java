/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-6-9，alex创建。 
 */
package cn.holdfun.cn.yaotv.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
	private static final Map<String, String> TOP50_RECORD = new ConcurrentHashMap<String, String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TOP50_RECORD.put("aaaa", "aaaa");
		TOP50_RECORD.clear();
		System.out.println(TOP50_RECORD.size());
		System.out.println(TOP50_RECORD.containsKey("aaaa"));

	}

}
