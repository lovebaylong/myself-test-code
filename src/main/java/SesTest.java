import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 版权所有 (C) 2014-2018 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2018-5-31，alex创建。 
 */

public class SesTest {
	private static ScheduledExecutorService ses = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("task 1 : " + System.currentTimeMillis());
			}
		}, 2, 1, TimeUnit.SECONDS);

		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("task 2 : " + System.currentTimeMillis());
			}
		}, 2, 5, TimeUnit.SECONDS);

		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("task 3 : " + System.currentTimeMillis());
			}
		}, 2, 10, TimeUnit.SECONDS);
	}

}
