/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-9-12，alex创建。 
 */
package cn.holdfun.cn.bar.test;

import cn.holdfun.cn.mp.test.HttpUtil;

public class BarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			for(int i = 130 ; i<140;i++){
				String url = "http://pts.holdfun.cn/bportal/api/pascreen/buyself?bu=e24bda88ce94420db00628642fc9372e&cn=Mira%2C%20Sof%C3%ADa%20%E4%BD%A0%E7%9C%8B%E5%91%80%EF%BC%8C%E6%88%91%E4%BA%B2%E7%88%B1%E7%9A%84%E7%B4%A2%E8%8F%B2%E4%BA%9A%EF%BC%8C%E6%88%91%E6%98%AF%E8%87%AA%E5%8A%A8%E5%BE%AA%E7%8E%AF%E7%AC%AC"+i+"%E6%AC%A1%E8%AF%B7%E6%B1%82&iu=http%3A%2F%2Fyaotv-ebusiness-test.oss-cn-shenzhen.aliyuncs.com%2Ffandom%2Fresources%2Fimages%2F2017%2F04%2F18%2F1c61cd69389a498b8bb84c1eeacc55f8.jpg&sk=b4u0cMsFtFB8%2F83cDzToAQ%3D%3D&un=%E6%B5%B7%E7%8E%8B%E5%AD%90&ul=http%3A%2F%2Fwx.qlogo.cn%2Fmmopen%2FO0c4eq6jj9EEl54Qd3E57U7qJluYYOyE5hoFqMMW4m2gYIvtCkrKqicngEUT7FTHfr7ahyMibJSypePIImmQiaibZOaTzBcicPsTy%2F0&pspu=d72d59f8837043238123ac03df7993d7";
				System.out.println(HttpUtil.sendHttpGetReqToServer(url));;
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
}
