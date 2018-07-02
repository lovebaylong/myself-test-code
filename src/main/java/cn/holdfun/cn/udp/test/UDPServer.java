/*
 * 版权所有 (C) 2014-2018 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2018-6-5，alex创建。 
 */
package cn.holdfun.cn.udp.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			byte[] buf = new byte[1024];
			// 服务端在3000端口监听接收到的数据
			DatagramSocket ds = new DatagramSocket(3000);
			// 接收从客户端发送过来的数据
			DatagramPacket dp_receive = new DatagramPacket(buf, 1024);
			System.out.println("server is on，waiting for client to send data......");
			boolean f = true;
			while (f) {
				// 服务器端接收来自客户端的数据
				ds.receive(dp_receive);
				String receiveData = new String(dp_receive.getData(), 0, dp_receive.getLength());
				System.out.println("receiveData == " + receiveData);
				// 由于dp_receive在接收了数据之后，其内部消息长度值会变为实际接收的消息的字节数，
				// 所以这里要将dp_receive的内部消息长度重新置为1024
				dp_receive.setLength(1024);
			}
			ds.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
