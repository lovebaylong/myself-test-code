/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-8-17，alex创建。 
 */
package random.test;

import cn.holdfun.cn.util.DateTimeUtil;

public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*for(int i=0;i<10;i++)
		System.out.println( RandomStringUtils.randomAlphanumeric(128));*/
		
		String fileName = "C:/DevTools/apache-tomcat-7.0.53/webapps/a.war";
		System.out.println(fileName.lastIndexOf("/"));
		
		System.out.println(DateTimeUtil.getDefinedDayStr(-7, DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
	}

}
