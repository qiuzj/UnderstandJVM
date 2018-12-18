package cn.javaee.jvm.option;

/**
 * java -Xmn10m cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCApplicationStoppedTime cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCApplicationConcurrentTime cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGC -XX:+PrintGCDateStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTaskTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1 cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * @author 二进制之路
 *
 */
public class PrintGCApplicationStoppedTimeDemo {
	public static void main(String[] args) {
		for (int i = 0; i < 300000; i++) {
			byte[] b = new byte[128];
		}
		System.out.println("Done");
	}
}