package cn.holdfun.cn.live.test;



public class Test3 {
	public static void main(String[] args) {
		/*JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		String aa = "{'comms':0,'content':'\\u0036\\u0036\\u0034\\u0039','createAt':{'date':16,'day':2,'hours':16,'minutes':58,'month':7,'seconds':10,'time':1471337890262,'timezoneOffset':-480,'year':116},'enterpriseUuid':'','floor':55709,'headimageurl':'51556565615615516','id':0,'images':'','likes':0,'nickname':'\\u0062\\u0061\\u0079\\u006c\\u006f\\u006e\\u0067','orderDesc':0,'parentHeadimageurl':'','parentNickname':'','parentUuid':'','repPoint':0,'repTuid':'1234','repType':1,'resType':2,'status':1,'topFlag':0,'type':1,'userUuid':'0fc5f1b3aa4e915f64a9c54b76d9f0db','uuid':'22bab3c18d8f4abb8a239331e7cbafe5'}";
		Comments eachComments = (Comments) JSONObject.toBean(JSONObject.fromObject(aa), Comments.class);*/
		
		String a="appid=wxd930ea5d5a258f4f&nonce_str=ibuaiVcKdpRxkhJA&userId=08f911a45abb6b426ef78f008155ac67";
		
		String SignTemp=a + "&key=192006250b4c09247ec02edce69f6a2d";
				String sign=getMD5(SignTemp.getBytes()).toUpperCase();
				System.out.println(sign);
	}
	
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
										// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
											// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
											// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
															// >>>
															// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
