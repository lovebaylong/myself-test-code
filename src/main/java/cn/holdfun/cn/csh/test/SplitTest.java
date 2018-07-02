/*
 * 版权所有 (C) 2014-2018 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2018-6-28，alex创建。 
 */
package cn.holdfun.cn.csh.test;

import java.util.Random;

public class SplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		split(0.1F, 3);
	}

	private static void split(Float moneyTotal, Integer numberTotal) {
		// 拼手气利是，需要随机按个数拆分总金额，并且要保证总金额不多也不少
		double remainMoney = moneyTotal.doubleValue();
		double previousBestMoney = 0D;
		int remainSize = numberTotal;
		int bestIndex = 0;
		double[] splitMoney = new double[numberTotal];
		for (int i = 1; i <= numberTotal; i++) {
			// remainSize 剩余的红包数量
			// remainMoney 剩余的钱
			double money = calcRandomPacketMoney(numberTotal, remainMoney, remainSize, i);
			remainSize--;
			remainMoney -= money;

			splitMoney[i - 1] = money;
			// 拼手气的第一个出现的最大金额即为手气最佳
			if (money > previousBestMoney){
				bestIndex = i - 1;
				previousBestMoney = money;
			}
			System.out.println("index=" + (i-1) + ", money = " + money + ", bestIndex = " + bestIndex + ", bestMoney = " + splitMoney[bestIndex]);
		}
		/*for (int i = 0; i < numberTotal; i++) 
			System.out.println(splitMoney[i]);*/
		System.out.println("bestIndex=" + bestIndex);
		System.out.println("bestMoney=" + splitMoney[bestIndex]);
	}

	private static double calcRandomPacketMoney(Integer numberTotal, double remainMoney, int remainSize, int packetIndex) {
		double money = 0.01D;
		if (packetIndex == numberTotal) {
			money = (double) Math.round(remainMoney * 100) / 100;
		} else {
			Random r = new Random();
			double min = 0.01; // 最小金额保证
			double max = remainMoney / remainSize * 2;
			money = r.nextDouble() * max;
			money = money <= min ? min : money;
			money = Math.floor(money * 100) / 100;
		}
		return money;
	}
}
