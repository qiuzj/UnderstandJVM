package cn.javaee.jvm.option;

import java.nio.*;

/**
 * java -XX:MaxDirectMemorySize=10m -XX:+PrintGC -XX:+DisableExplicitGC cn/javaee/jvm/option/DisableExplicitGCDemo
 * 
 * @author 二进制之路
 *
 */
public class DisableExplicitGCDemo {
	public static void main(String[] args) {
		for (int i = 0; i < 300000; i++) {
			ByteBuffer.allocateDirect(128);
		}
		System.out.println("Done");
	}
}