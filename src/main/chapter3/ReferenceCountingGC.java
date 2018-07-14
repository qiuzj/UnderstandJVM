package chapter3;

/**
 * testGC()方法执行后，objA和objB会不会被GC呢？ 
 * java -Xmn2m -Xms10m -Xmx10m -XX:+PrintGCDetails chapter3.ReferenceCountingGC
 * 
 * @author zzm
 */
public class ReferenceCountingGC {

	public Object instance = null;

	private static final int _1MB = 1024 * 1024;

	/**
	 * 这个成员属性的唯一意义就是占点内存，以便在能在GC日志中看清楚是否有回收过
	 */
	private byte[] bigSize = new byte[4 * _1MB];

	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;

		objA = null;
		objB = null;

		// 假设在这行发生GC，objA和objB是否能被回收？
		System.gc();
	}
	
	public static void main(String[] args) {
		testGC();
	}
}
/*
[GC [PSYoungGen: 661K->504K(1536K)] 4757K->4624K(9728K), 0.0013385 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC [PSYoungGen: 504K->0K(1536K)] [ParOldGen: 4120K->466K(8192K)] 4624K->466K(9728K) [PSPermGen: 2573K->2572K(21504K)], 0.0123334 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 1536K, used 20K [0x00000000ffe00000, 0x0000000100000000, 0x0000000100000000)
  eden space 1024K, 2% used [0x00000000ffe00000,0x00000000ffe053f8,0x00000000fff00000)
  from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
  to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
 ParOldGen       total 8192K, used 466K [0x00000000ff600000, 0x00000000ffe00000, 0x00000000ffe00000)
  object space 8192K, 5% used [0x00000000ff600000,0x00000000ff674b58,0x00000000ffe00000)
 PSPermGen       total 21504K, used 2579K [0x00000000fa400000, 0x00000000fb900000, 0x00000000ff600000)
  object space 21504K, 11% used [0x00000000fa400000,0x00000000fa684f00,0x00000000fb900000)
*/
