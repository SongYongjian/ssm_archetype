package com.hand.hls.util.component;

/**
 * 工具
 * @author 杨润康
 *
 */
public class Util {
	public static void time_6s(){
		try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void time_10s(){
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void time_8s(){
		try {
			Thread.sleep(8*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
