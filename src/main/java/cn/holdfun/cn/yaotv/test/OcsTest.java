package cn.holdfun.cn.yaotv.test;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

public class OcsTest {


	private static String memcacheIp = "629c887123c411e4.m.cnbjalicm12pub001.ocs.aliyuncs.com";

	private static String memcachePort="11211";

	private static String memcacheUsername="629c887123c411e4";

	private static String memcachePwd="zq_2014_tv";

	private static MemcachedClient mcc = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String key = "connect_user_lottery_limit-e66be3fe131445ac872c25cb4698ee37-oMiw7t8wqMDKZ2Nss1gyYJ33Nyz4";
		createClient();
		
		MemcachedClient cachedClient = createClient();
		Object o = cachedClient == null ? null : cachedClient.get(key);
		System.out.println(o);
	}

	
	private static MemcachedClient createClient() {
		if (mcc != null)
			return mcc;

		AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler(memcacheUsername, memcachePwd));
		try {
			mcc = new MemcachedClient(new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY).setAuthDescriptor(ad).build(),
					AddrUtil.getAddresses(memcacheIp + ":" + memcachePort));
			return mcc;
		} catch (NumberFormatException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
}
