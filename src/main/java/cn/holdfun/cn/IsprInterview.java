package cn.holdfun.cn;

import java.io.UnsupportedEncodingException;

public class IsprInterview {
	private static int iNum1 = 10;
	public static int iNum2 = 20;

	public int GetNum1() {
		return iNum1;
	}

	public static void main(String[] args) {
		IsprInterview oIspr = new IsprInterview();
		System.out.println(IsprInterview.iNum2);
		System.out.println("abc".getBytes());
		try {
			System.out.println("abc".getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
