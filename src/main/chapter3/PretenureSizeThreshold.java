package chapter3;

/**
 * VM参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseConcMarkSweepGC
 */
public class PretenureSizeThreshold {

	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}
	private static final int _1MB = 1024 * 1024;
	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB];  //直接分配在老年代中
	}

}
/*
-XX:+UseSerialGC：
Heap
def new generation   total 9216K, used 999K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
 eden space 8192K,  12% used [0x00000000f9a00000, 0x00000000f9af9f28, 0x00000000fa200000)
 from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
 to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
tenured generation   total 10240K, used 4096K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
  the space 10240K,  40% used [0x00000000fa400000, 0x00000000fa800010, 0x00000000fa800200, 0x00000000fae00000) 直接进入老年代
compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
  the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085128, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.

-XX:+UseConcMarkSweepGC：
Heap
 par new generation   total 9216K, used 836K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 8192K,  10% used [0x00000000f9a00000, 0x00000000f9ad1178, 0x00000000fa200000)
  from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
  to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 concurrent mark-sweep generation total 10240K, used 4096K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000) 直接进入老年代
 concurrent-mark-sweep perm gen total 21248K, used 2581K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)

*/