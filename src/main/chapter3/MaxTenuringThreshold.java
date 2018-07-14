package chapter3;

/**
 * VM参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
 */
public class MaxTenuringThreshold {

	private static final int _1MB = 1024 * 1024;

	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		testTenuringThreshold();
	}

}
/*
-XX:+UseSerialGC -XX:MaxTenuringThreshold=1：
[GC[DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 1)
- age   1:     741376 bytes,     741376 total
: 5187K->724K(9216K), 0.2784217 secs] 5187K->4820K(19456K), 0.2784683 secs] [Times: user=0.00 sys=0.00, real=0.28 secs] 
[GC[DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 1)
- age   1:        136 bytes,        136 total
: 4904K->0K(9216K), 0.0014060 secs] 9000K->4819K(19456K), 0.0014265 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4260K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 8192K,  52% used [0x00000000f9a00000, 0x00000000f9e28fd0, 0x00000000fa200000)
  from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200088, 0x00000000fa300000)
  to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 tenured generation   total 10240K, used 4819K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
   the space 10240K,  47% used [0x00000000fa400000, 0x00000000fa8b4db0, 0x00000000fa8b4e00, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085170, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.

-XX:+UseSerialGC -XX:MaxTenuringThreshold=15：没测出预期结果，allocation1仍然被移动到老年代了
[GC[DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 15)
- age   1:     741376 bytes,     741376 total
: 5187K->724K(9216K), 0.0031787 secs] 5187K->4820K(19456K), 0.0032170 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC[DefNew
Desired survivor size 524288 bytes, new threshold 15 (max 15)
- age   1:        136 bytes,        136 total
: 4904K->0K(9216K), 0.0013089 secs] 9000K->4819K(19456K), 0.0013275 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4260K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 8192K,  52% used [0x00000000f9a00000, 0x00000000f9e28fd0, 0x00000000fa200000)
  from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200088, 0x00000000fa300000)
  to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 tenured generation   total 10240K, used 4819K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
   the space 10240K,  47% used [0x00000000fa400000, 0x00000000fa8b4db0, 0x00000000fa8b4e00, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085170, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.


-XX:+UseSerialGC -XX:MaxTenuringThreshold=15：
加大堆内存：-verbose:gc -Xms30M -Xmx30M -Xmn16M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
[GC[DefNew
Desired survivor size 819200 bytes, new threshold 15 (max 15)
- age   1:     741376 bytes,     741376 total
: 9504K->724K(14784K), 0.0045982 secs] 9504K->4820K(29120K), 0.0046432 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
Heap
 def new generation   total 14784K, used 5087K [0x00000000f9000000, 0x00000000fa000000, 0x00000000fa000000)
  eden space 13184K,  33% used [0x00000000f9000000, 0x00000000f9442e30, 0x00000000f9ce0000)
  from space 1600K,  45% used [0x00000000f9e70000, 0x00000000f9f25000, 0x00000000fa000000)
  to   space 1600K,   0% used [0x00000000f9ce0000, 0x00000000f9ce0000, 0x00000000f9e70000)
 tenured generation   total 14336K, used 4096K [0x00000000fa000000, 0x00000000fae00000, 0x00000000fae00000)
   the space 14336K,  28% used [0x00000000fa000000, 0x00000000fa400010, 0x00000000fa400200, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085170, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.

*/
