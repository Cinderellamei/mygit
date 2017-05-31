package com.mei;

//产生随机数
import java.util.Random;

public class RandomTest {
	public static void main(String [] args) {
		Random rand=new Random();
		int data []=new int[7];
		int foot=0;
		while(foot<7) {
			int t=rand.nextInt(37);
			if(!isEmpty(data,t)) {
				data[foot++]=t;
			}
		}
		
		for(int i=0;i<data.length;i++) {
			System.out.println(data[i]);
		}
	}
	
	public static boolean isEmpty(int [] temp,int num) {
		if(num==0) {
			return true;
		}
		for(int i=0;i<temp.length;i++) {
			if(num==temp[i]) {
				return true;
			}
		}
		return false;
	}
}
