import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.StringTokenizer;

/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-10-12，alex创建。 
 */

public class SystemInfoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
		
		getJvmPIDOnWindows();
		
		/*StringTokenizer ts = new StringTokenizer("30926 26195 9625 6608 6303 6140 5959 5813");
		System.out.println(ts.countTokens());
		while(ts.hasMoreTokens())
			System.out.println(ts.nextToken());*/
		
		System.out.println(getThreadCountForWindows());
		
	}
	public static void getJvmPIDOnWindows() {
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		String pid = runtime.getName().split("@")[0];
		System.out.println("runtime.getName():" + runtime.getName());
		System.out.println("PID of JVM:" + pid);
		
	}
	
	public static int getThreadCountForWindows() {
		String command = "wmic process " + 2376 + "  list brief";
		int count = 0;
		BufferedReader in = null;
		try {
			Process pro = Runtime.getRuntime().exec(command);
			in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			// testGetInput(in);
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			String info = in.readLine();
			System.out.println(info);
			StringTokenizer ts = new StringTokenizer(info);
			int i = 1;

			while (ts.hasMoreTokens()) {
				i++;
				ts.nextToken();
				if (i == 5) {
					count = Integer.parseInt(ts.nextToken());
				}
			}
			in.close();
			pro.destroy();
		} catch (Exception e) {
			System.out.println("getThreadCountForWindows()报异常：" + e);
		}
		return count;
	}
}
