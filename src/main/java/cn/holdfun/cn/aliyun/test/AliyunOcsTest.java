package cn.holdfun.cn.aliyun.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AliyunOcsTest {
	private static final Log logger = LogFactory.getLog(AliyunOcsTest.class);
	//测试环境
	/*private String memcacheIp = "182.92.185.131";
	private String memcachePort = "11211";
	private String memcacheUsername = "";
	private String memcachePwd = "";*/
	
	//正式环境
	private String memcacheIp = "629c887123c411e4.m.cnbjalicm12pub001.ocs.aliyuncs.com";
	private String memcachePort = "11211";
	private String memcacheUsername = "629c887123c411e4";
	private String memcachePwd = "zq_2014_tv";
	
	private MemcachedClient mcc = null;

	private static final String TEST_KEY = "test_ocs_key-2016-08-23-a-b-c-d-e-f";

	private MemcachedClient createClient() {
		if (mcc != null)
			return mcc;

		AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler(memcacheUsername, memcachePwd));
		try {
			if (StringUtils.isEmpty(memcacheUsername) && StringUtils.isEmpty(memcachePwd))
				mcc = new MemcachedClient(new InetSocketAddress(memcacheIp, Integer.valueOf(memcachePort)));
			else
				mcc = new MemcachedClient(new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY).setAuthDescriptor(ad).build(),
						AddrUtil.getAddresses(memcacheIp + ":" + memcachePort));
			return mcc;
		} catch (NumberFormatException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final AliyunOcsTest t = new AliyunOcsTest();
		
		logger.info("开始创建ocs客户端对象");
		t.createClient();
		logger.info("结束创建ocs客户端对象");
		

		logger.info("开始执行阿里云OCS组件测试...");;
		logger.info("初始化设置key=" + TEST_KEY + ", 初始化值 =" + 0 + ", 保存有效期=" + (7 * 24 * 60 * 60));
		t.mcc.set(TEST_KEY, (7 * 24 * 60 * 60), String.valueOf(0));
		
		logger.info("查询初始化key的值，key=" + TEST_KEY + ", 当前值=" + t.mcc.get(TEST_KEY));
		logger.info("开始测试key递增，测试线程4个，调用Api=net.spy.memcached.MemcachedClient.incr(String key, long by, long def, int exp)，测试key="+TEST_KEY);
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						logger.info("递增后的新值 = " + t.mcc.incr(TEST_KEY, 1, 1, (7 * 24 * 60 * 60)));
					}
				}
			}).start();
		}
	}
}
