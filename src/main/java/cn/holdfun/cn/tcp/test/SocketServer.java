/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-7-13，alex创建。 
 */
package cn.holdfun.cn.tcp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.start();
	}
	
	void start(){
		try {// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(10086);// 1024-65535的某个端口
			// 2、调用accept()方法开始监听，等待客户端的连接
			int count = 0;
			while (true) {
				Socket socket = serverSocket.accept();
				ServerSocketThread serverSocketThread = new ServerSocketThread(socket, ++count);
				serverSocketThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ServerSocketThread extends Thread {
		private Socket socket;
		private int count;

		public ServerSocketThread(Socket socket, int count) {
			super();
			this.socket = socket;
			this.count = count;
			this.setName("我是线程" + count);
		}

		public void run() {

			try {
				// 3、获取输入流，并读取客户端信息
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String info = null;
				while ((info = br.readLine()) != null) {
					System.out.println("Hello,我是服务器，客户端说：" + info);
				}
				socket.shutdownInput();// 关闭输入流
				// 4、获取输出流，响应客户端的请求
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				
				for(int i=0;i<100;i++){
					pw.write(String.valueOf(Thread.currentThread().getName()) + ", 我使用的端口是：" + socket.getPort() + ", 我是第"+i+"次响应");
					pw.flush();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 5、关闭资源
				pw.close();
				os.close();
				br.close();
				isr.close();
				is.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
