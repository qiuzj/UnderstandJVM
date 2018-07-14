package chapter3;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * eden space 8192K、from space 1024K、to space 1024K、old space 10240K
 */
public class EdenAllocation {

	private static final int _1MB = 1024 * 1024;

	public static void testAllocation() {
	 	byte[] allocation1, allocation2, allocation3, allocation4;
	 	allocation1 = new byte[2 * _1MB];
	 	allocation2 = new byte[2 * _1MB];
	 	allocation3 = new byte[2 * _1MB];
	 	allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC, 9M不够分
	 }
	
	public static void main(String[] args) {
		testAllocation();
	}

}
/*
-XX:+UseSerialGC：
给allocation4分配内存时，发现Eden已经被占用了6MB，不足以再分配4MB，因此发生Minor GC。3个2MB的对象无法放入Survivor空间，
因此通过分配担保机制提前转移到老年代去
[GC[DefNew: 6980K->471K(9216K), 0.0036181 secs] 6980K->6615K(19456K), 0.0036749 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4813K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)	// allocation4占用4MB
  eden space 8192K,  53% used [0x00000000f9a00000, 0x00000000f9e3d7e0, 0x00000000fa200000)
  from space 1024K,  46% used [0x00000000fa300000, 0x00000000fa375e68, 0x00000000fa400000)
  to   space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
 tenured generation   total 10240K, used 6144K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000) // allocation1~3占用3*2=6MB
   the space 10240K,  60% used [0x00000000fa400000, 0x00000000faa00030, 0x00000000faa00200, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2614K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb08db40, 0x00000000fb08dc00, 0x00000000fc2c0000)
No shared spaces configured.


-XX:+UseConcMarkSweepGC：
[GC[ParNew: 6980K->484K(9216K), 0.0026009 secs] 6980K->6630K(19456K), 0.0026541 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC [1 CMS-initial-mark: 6146K(10240K)] 10726K(19456K), 0.0003553 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 par new generation   total 9216K, used 4826K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 8192K,  53% used [0x00000000f9a00000, 0x00000000f9e3d7e0, 0x00000000fa200000)
  from space 1024K,  47% used [0x00000000fa300000, 0x00000000fa379278, 0x00000000fa400000)
  to   space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
 concurrent mark-sweep generation total 10240K, used 6146K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
 concurrent-mark-sweep perm gen total 21248K, used 2615K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
[CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 

*/
