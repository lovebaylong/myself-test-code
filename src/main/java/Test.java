import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-8-29，alex创建。 
 */

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String encryptedData = "QdQmGn8Go5swja9wcwiRha9IOY53Q/AYtxYczwoGDTYZQ1S3aaQwM6mE5vy0zFqJxBOFq0a+ClYCiQfZNDpGDP96QsYlbQDoCMzMgB9ZbegaPrw4q2s3P5nk5N0sfAc7soYDe3yvNvRXJYvE3LQ7ax3t5qBvj5a8YW3LJEQo1lnPiJywpCP1KFmq8WVK4BWgfFMd2hy8OAWXRZBPr3q8GrrKxc9p7iMEvKMYlQnWzCDDyxW1YKf8OE9M2gaCLGKOfgtqsdvU/UYOWxiYA6L5LzWTWalMK3nNeE7T24sh2idHSWYK70hIoKGlR4OLpLiFK62b0PV+NrVTaDInavnho7PHTb3DYA+hyA/L8iCJiqtzO72osCurYTMi9ndMVReMhghOIyLG8U4XWTnI76XYMaAghu2vr5Mcyyl6HWwW9A0lXARib8+6BDdGiwiFHieR+H3AOkoTbT3DijjJu1rkCfwPw2PNUM5TTxAizYhxmR1/v1gQJzungHPcMldWOw4HEGSDy+194cDnAE+4NZfaJQ==";
		String sessionKey = "vbUjxfAXzVH99xw5U+2SEQ==";
		String iv = "X5MSp0vnbK9vrAaOlP0GEg==";

		JSONObject resultJson = getUserInfo(encryptedData, sessionKey, iv);
		System.out.println(resultJson.toString());
	}

	/**
	 * 解密用户敏感数据获取用户信息
	 * 
	 * @param sessionKey 数据进行加密签名的密钥
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
	 * @param iv 加密算法的初始向量
	 */
	public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decodeBase64(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decodeBase64(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decodeBase64(iv);
		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.fromObject(result);
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterSpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
