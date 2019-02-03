package cn.javaee.jvm.option;

/**
 * java -Xmn10m cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCApplicationStoppedTime cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCApplicationConcurrentTime cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintCommandLineFlags cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -client -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintCommandLineFlags cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -server -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintCommandLineFlags cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * java -client -Xmn10m -XX:+PrintFlagsFinal -version|grep UseParallel
 * java -server -Xmn10m -XX:+PrintFlagsFinal -version|grep UseParallel
 * 
 * java -client -XX:+PrintFlagsFinal -version|grep UseParallel
 * java -server -XX:+PrintFlagsFinal -version|grep UseParallel
 * 
 * java -client -Xmn10m -XX:+UseParallelGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * java -server -Xmn10m -XX:+UseParallelGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * 
 * java -client -XX:+UseSerialGC -XX:+PrintFlagsFinal -version|grep UseConcMarkSweepGC
 * java -server -XX:+UseSerialGC -XX:+PrintFlagsFinal -version|grep UseConcMarkSweepGC
 * 
 * java -client -XX:+UseParallelGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * java -server -XX:+UseParallelGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * 
 * java -client -XX:+UseParNewGC -XX:+PrintFlagsFinal -version|grep UseConcMarkSweepGC
 * java -server -XX:+UseParNewGC -XX:+PrintFlagsFinal -version|grep UseConcMarkSweepGC
 * 
 * java -client -XX:-UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -version|grep UseSerial
 * java -server -XX:-UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -version|grep UseSerial
 * 
 * java -client -XX:-UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -version|grep UseSerial
 * java -server -XX:-UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -version|grep UseSerial
 * 
 * java -server -XX:+UseSerialGC -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -version|grep UseSerial
 * 
 * java -client -XX:+UseParallelOldGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * java -server -XX:+UseParallelOldGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * 
 * java -server -XX:+UseParallelOldGC -XX:+UseSerialGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * java -server -XX:+UseParallelOldGC -XX:+UseParNewGC -XX:+PrintFlagsFinal -version|grep UseParallel
 * 
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGC -XX:+PrintGCDateStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -verbose:gc cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCCause cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTaskTimeStamps cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1 cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * 大小不限制：
 * java -Xmn10m -XX:+PrintGCDetails -Xloggc:D:/gcgc.log cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -Xloggc:D:/gcgc.log -XX:GCLogFileSize=20k cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -Xloggc:D:/gcgc.log -XX:GCLogFileSize=20k -XX:+UseGCLogFileRotation cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * java -Xmn10m -Xloggc:D:/gcgc.log cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGC -XX:+PrintGCTimeStamps -Xloggc:D:/gcgc.log cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * 日志文件滚动，最大为3个日志文件。开启日志文件滚动时，如果不设置或设置的文件大小不足8K，默认为8K。
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:D:/gcgc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=3 cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:D:/gcgc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=3 -XX:GCLogFileSize=1k cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:D:/gcgc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=3 -XX:GCLogFileSize=11k cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo
 * 
 * java -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:D:/gcgc.log -XX:+UseGCLogFileRotation cn/javaee/jvm/option/PrintGCApplicationStoppedTimeDemo：
 * To enable GC log rotation, use -Xloggc:<filename> -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=<num_of_files>
 * 
 * @author 二进制之路
 *
 */
public class PrintGCApplicationStoppedTimeDemo {
	public static void main(String[] args) {
//		for (;;) {
			for (int i = 0; i < 500000; i++) {
				byte[] b = new byte[128];
			}
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("Done");
	}
}