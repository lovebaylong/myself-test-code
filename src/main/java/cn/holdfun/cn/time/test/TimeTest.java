/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-6-26，alex创建。 
 */
package cn.holdfun.cn.time.test;

import org.apache.commons.lang.math.RandomUtils;

import cn.holdfun.cn.util.DateTimeUtil;

public class TimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 2017-06-26 09:56 ~ 2017-06-30 09:56
		/*long startTimeMs = DateTimeUtil.toDate("2017-06-26 09:56:00").getTime();
		long stopTimeMs = DateTimeUtil.toDate("2017-06-30 09:56:00").getTime();
		Long frequency = (startTimeMs - stopTimeMs) / 10000;
		System.out.println(frequency);
		System.out.println(frequency.intValue());
		
		for(int i=0;i<100;i++){
			System.out.println(RandomUtils.nextInt(frequency.intValue()));
		}*/
		
		long st = DateTimeUtil.toDate("2017-08-01 00:00:00").getTime();
		System.out.println(st);
		System.out.println(st/1000);
	}

}
