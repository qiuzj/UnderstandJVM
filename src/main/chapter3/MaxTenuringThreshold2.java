package chapter3;

/**
 * VM参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
 */
public class MaxTenuringThreshold2 {
	private static final int _1MB = 1024 * 1024;

	@SuppressWarnings("unused")
	public static void testTenuringThreshold2() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];   // allocation1+allocation2大于survivo空间一半
		allocation2 = new byte[_1MB / 4];  
		allocation3 = new byte[8 * _1MB];
		allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];
	}


	public static void main(String[] args) {
		testTenuringThreshold2();
	}
}
/*
-XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC：
-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
[GC[DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 15)
- age   1:    1003360 bytes,    1003360 total
: 5280K->979K(9216K), 0.0033947 secs] 5280K->5075K(19456K), 0.0034342 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC[DefNew
Desired survivor size 524288 bytes, new threshold 15 (max 15)
- age   1:        136 bytes,        136 total
: 5239K->0K(9216K), 0.0015620 secs] 9335K->5075K(19456K), 0.0015845 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4260K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 8192K,  52% used [0x00000000f9a00000, 0x00000000f9e28fd0, 0x00000000fa200000)
  from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200088, 0x00000000fa300000)
  to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 tenured generation   total 10240K, used 5075K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
   the space 10240K,  49% used [0x00000000fa400000, 0x00000000fa8f4d10, 0x00000000fa8f4e00, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085188, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.


JDK1.7.0_79：
-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=5 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
[GC[DefNew
Desired survivor size 720896 bytes, new threshold 1 (max 15)
- age   1:    1003360 bytes,    1003360 total
: 5500K->979K(8832K), 0.0033758 secs] 5500K->5075K(19072K), 0.0034172 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC[DefNew
Desired survivor size 720896 bytes, new threshold 15 (max 15)
- age   1:        136 bytes,        136 total
: 5152K->0K(8832K), 0.0014791 secs] 9248K->5075K(19072K), 0.0015043 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 8832K, used 4244K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 7424K,  57% used [0x00000000f9a00000, 0x00000000f9e25260, 0x00000000fa140000)
  from space 1408K,   0% used [0x00000000fa140000, 0x00000000fa140088, 0x00000000fa2a0000)
  to   space 1408K,   0% used [0x00000000fa2a0000, 0x00000000fa2a0000, 0x00000000fa400000)
 tenured generation   total 10240K, used 5075K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
   the space 10240K,  49% used [0x00000000fa400000, 0x00000000fa8f4d10, 0x00000000fa8f4e00, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085188, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.


JDK1.6.0_45：
-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=5 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
[GC [DefNew
Desired survivor size 720896 bytes, new threshold 15 (max 15)
- age   1:     691392 bytes,     691392 total
: 4905K->675K(8832K), 0.0035850 secs] 4905K->4771K(19072K), 0.0036106 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
[GC [DefNew
Desired survivor size 720896 bytes, new threshold 15 (max 15)
- age   1:        136 bytes,        136 total
- age   2:     691120 bytes,     691256 total
: 4919K->675K(8832K), 0.0005274 secs] 9015K->4771K(19072K), 0.0005408 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 8832K, used 4919K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 7424K,  57% used [0x00000000f9a00000, 0x00000000f9e25268, 0x00000000fa140000)
  from space 1408K,  47% used [0x00000000fa140000, 0x00000000fa1e8c38, 0x00000000fa2a0000)
  to   space 1408K,   0% used [0x00000000fa2a0000, 0x00000000fa2a0000, 0x00000000fa400000)
 tenured generation   total 10240K, used 4096K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
   the space 10240K,  40% used [0x00000000fa400000, 0x00000000fa800010, 0x00000000fa800200, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 3042K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  14% used [0x00000000fae00000, 0x00000000fb0f8bc0, 0x00000000fb0f8c00, 0x00000000fc2c0000)
No shared spaces configured.

*/
