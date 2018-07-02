package cn.holdfun.cn.util;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 湖南经视流量包手机加密工具类，由湖南移动开发方提供
 * @author zhou.xu
 * @date： 日期：2014-5-13 时间：下午03:51:35
 */
@SuppressWarnings("restriction")
public class HnjsFlowCryptDesUtil {
    private byte[] desKey;  
    
    public HnjsFlowCryptDesUtil() {
    	this.desKey = "mobileNum".getBytes();  
	}
    
    public HnjsFlowCryptDesUtil(String desKey) {  
        this.desKey = desKey.getBytes();  
    }  
  
    public byte[] desEncrypt(byte[] plainText) throws Exception {  
        SecureRandom sr = new SecureRandom();  
        byte rawKeyData[] = desKey;  
        DESKeySpec dks = new DESKeySpec(rawKeyData);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey key = keyFactory.generateSecret(dks);  
        Cipher cipher = Cipher.getInstance("DES");  
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
        byte data[] = plainText;  
        byte encryptedData[] = cipher.doFinal(data);  
        return encryptedData;  
    }  
  
    public byte[] desDecrypt(byte[] encryptText) throws Exception {  
        SecureRandom sr = new SecureRandom();  
        byte rawKeyData[] = desKey;  
        DESKeySpec dks = new DESKeySpec(rawKeyData);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey key = keyFactory.generateSecret(dks);  
        Cipher cipher = Cipher.getInstance("DES");  
        cipher.init(Cipher.DECRYPT_MODE, key, sr);  
        byte encryptedData[] = encryptText;  
        byte decryptedData[] = cipher.doFinal(encryptedData);  
        return decryptedData;  
    }  
  
    public String encrypt(String input) throws Exception {  
        return base64Encode(desEncrypt(input.getBytes()));  
    }  
  
    public String decrypt(String input) throws Exception {  
        byte[] result = base64Decode(input);  
        return new String(desDecrypt(result));  
    }  
  
    public static String base64Encode(byte[] s) {  
        if (s == null)  
            return null;  
        BASE64Encoder b = new sun.misc.BASE64Encoder();  
        return b.encode(s);  
    }  
  
    public static byte[] base64Decode(String s) throws IOException {  
        if (s == null)  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        byte[] b = decoder.decodeBuffer(s);  
        return b;  
    }  
  
    public static void main(String[] args) throws Exception {  
        String key = "mobileNum";  
        String input = "13786165441";  
        HnjsFlowCryptDesUtil crypt = new HnjsFlowCryptDesUtil(key);  
        System.out.println("Encode:" + crypt.encrypt(input));  
        System.out.println("Decode:" + crypt.decrypt(crypt.encrypt(input)));  
    } 
}
