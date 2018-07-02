package cn.holdfun.cn.mp.test;

import java.util.ArrayList;
import java.util.List;

public class Triangles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		triangles(15);
	}
	
	private static void triangles(int level){
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		
		for(int i=0;i<level;i++){
			System.out.println(l);
			l.add(0);
			List<Integer> l2 = new ArrayList<Integer>();
			for(int j = 0; j < l.size(); j++){
				int f = j-1 < 0 ? l.size() -1 : j-1;
				l2.add(l.get(f)+l.get(j));
			}
			l = l2;
		}
	}

}
