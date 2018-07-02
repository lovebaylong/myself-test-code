package cn.holdfun.cn.robot.test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.holdfun.cn.util.DateTimeUtil;

/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-4-10，alex创建。 
 */

public class ThreadTest {
	private static final ExecutorService es = Executors.newCachedThreadPool();
	private static final Map<String, Thread> EXECUTE_TASK = new HashMap<String, Thread>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
	}
	
	private void start(){
		Thread tt = new TestThread();
		tt.setName("testThread");
		EXECUTE_TASK.put(tt.getName(), tt);
		es.submit(tt);
	}

	private void stop(String threadName){
		if(EXECUTE_TASK.containsKey(threadName))
			EXECUTE_TASK.get(threadName).interrupt();
	}
	
	class TestThread extends Thread{
		@Override
		public void run() {
			System.out.println(DateTimeUtil.getDateTimeString());
		}
	}
}
