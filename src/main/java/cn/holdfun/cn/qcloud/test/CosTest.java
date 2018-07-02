/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-6-5，alex创建。 
 */
package cn.holdfun.cn.qcloud.test;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

public class CosTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化秘钥信息
		long appId = 1253690549;
		String secretId = "AKIDSsCNSNpkCYjokVxRdeT7arxt33CANdCc";
		String secretKey = "QegWDk6GE4mIhDmBOQODqHWZTBg2CJLD";
		// 设置要操作的bucket
		String bucketName = "xshare";
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);

		// 初始化客户端配置(如设置园区)
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion("gz");

		// 生成客户端
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		// 上传文件
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, "/test/image/bd42481614fe43d29a29004af0fa9cd6.jpg", "D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6.jpg");
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		System.out.println(uploadFileRet);
	}

}
