/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-8-14，alex创建。 
 */
package cn.holdfun.cn.digest.test;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha1Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String rawData = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}";
		String sessionKey = "HyVFkGl5F5OQWJZZaNzBBg==";
		String signature = "75e81ceda165f4ffa64f4068af58c64b8f54b88c";
		/*
		String localSignature = DigestUtils.sha1Hex(rawData + sessionKey);
		System.out.println(localSignature);
		System.out.println(signature.equals(localSignature));*/
		System.out.println(DigestUtils.md5Hex(sessionKey));;
	}

}
