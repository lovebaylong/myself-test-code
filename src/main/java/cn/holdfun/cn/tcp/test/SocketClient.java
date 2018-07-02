/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-7-13，alex创建。 
 */
package cn.holdfun.cn.tcp.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import cn.holdfun.cn.util.DateTimeUtil;
import cn.holdfun.cn.util.UuidUtil;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				SocketClient sc = new SocketClient();
				while (true) {
					sc.send2Server(1);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				SocketClient sc = new SocketClient();
				while (true) {
					sc.send2Server(2);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {

			public void run() {
				SocketClient sc = new SocketClient();
				while (true) {
					sc.send2Server(3);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	void send2Server(int clientNu) {

		try {// 客户端
				// 1、创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("127.0.0.1", 10086);
			// 2、获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();// 字节输出流
			PrintWriter pw = new PrintWriter(os);// 将输出流包装成打印流
			pw.write(UuidUtil.randomUUID());
			pw.flush();
			socket.shutdownOutput();
			// 3、获取输入流，并读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while(true){
				while ((info = br.readLine()) != null) {
					System.out.println("Hello,我是客户端" + clientNu + "，服务器说：" + info);
				}
			}

			// 4、关闭资源
			//br.close();
			//is.close();
			//pw.close();
			//os.close();
			//socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
