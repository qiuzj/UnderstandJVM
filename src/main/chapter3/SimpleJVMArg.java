package chapter3;
 
/**
* @author idouba
* Use shortest code demo jvm allocation, gc, and someting in gc.
*
* In details
* 1) sizing of young generation (eden space，survivor space),old generation.
* 2) allocation in eden space, gc in young generation,
* 3) working with survivor space and with old generation.
*
*/
public class SimpleJVMArg {
 
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        demo();
    }
 
    /**
     * VM arg：
     * -verbose:gc -Xms200M -Xmx200M -Xmn100M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * -verbose:gc -Xms200M -Xmx200M -Xmn100M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
     *
     */
    @SuppressWarnings("unused")
    public static void demo() { // new:100M, eden:80M, one survivor:10M, new+one survivor:90M, old:100M

        final int tenMB = 10* 1024 * 1024; // 10M
 
        byte[] alloc1, alloc2, alloc3;
 
        alloc1 = new byte[tenMB / 5]; // 2M
        alloc2 = new byte[5 * tenMB]; // 50M
        alloc3 = new byte[4 * tenMB]; // 40M
        alloc3 = null;
        alloc3 = new byte[6 * tenMB]; // 60M
    }
}

/*
JDK1.7.0_79：
-verbose:gc -Xms200M -Xmx200M -Xmn100M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
[GC[DefNew
Desired survivor size 5242880 bytes, new threshold 1 (max 1)
- age   1:    2576080 bytes,    2576080 total    // alloc3 = new byte[4 * tenMB];触发第一次Minor GC，回收后alloc1移动到From Survivor区域，年龄变为1. alloc2直接进入老年代
: 56525K->2515K(92160K), 0.0181415 secs] 56525K->53715K(194560K), 0.0181838 secs] [Times: user=0.00 sys=0.02, real=0.02 secs]   // 56525K->2515回收了50M的alloc2
[GC[DefNew
Desired survivor size 5242880 bytes, new threshold 1 (max 1)
- age   1:        136 bytes,        136 total    // alloc3 = null;alloc3 = new byte[6 * tenMB];触发第二次Minor GC，回收alloc3内存40M，alloc1的年龄到达MaxTenuringThreshold所以也进入老年代
: 44318K->0K(92160K), 0.0019227 secs] 95518K->53715K(194560K), 0.0019476 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]    // alloc3的40M内存被回收
Heap
 def new generation   total 92160K, used 63078K [0x00000000ee600000, 0x00000000f4a00000, 0x00000000f4a00000)
  eden space 81920K,  77% used [0x00000000ee600000, 0x00000000f2399a10, 0x00000000f3600000)
  from space 10240K,   0% used [0x00000000f3600000, 0x00000000f3600088, 0x00000000f4000000)
  to   space 10240K,   0% used [0x00000000f4000000, 0x00000000f4000000, 0x00000000f4a00000)
 tenured generation   total 102400K, used 53715K [0x00000000f4a00000, 0x00000000fae00000, 0x00000000fae00000)
   the space 102400K,  52% used [0x00000000f4a00000, 0x00000000f7e74c90, 0x00000000f7e74e00, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085178, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.


JDK1.7.0_79：
-verbose:gc -Xms200M -Xmx200M -Xmn100M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
[GC[DefNew
Desired survivor size 5242880 bytes, new threshold 15 (max 15)
- age   1:    2576080 bytes,    2576080 total    // alloc1年龄为1
: 54886K->2515K(92160K), 0.0180069 secs] 54886K->53715K(194560K), 0.0180460 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
[GC[DefNew
Desired survivor size 5242880 bytes, new threshold 15 (max 15)
- age   1:        136 bytes,        136 total
- age   2:    2575488 bytes,    2575624 total    // alloc1年龄为2
: 45114K->2515K(92160K), 0.0019330 secs] 96314K->53715K(194560K), 0.0019516 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]   // alloc1仍在survivor
Heap
 def new generation   total 92160K, used 65593K [0x00000000ee600000, 0x00000000f4a00000, 0x00000000f4a00000)
  eden space 81920K,  77% used [0x00000000ee600000, 0x00000000f2399a10, 0x00000000f3600000)  // alloc3 60M
  from space 10240K,  24% used [0x00000000f3600000, 0x00000000f3874d08, 0x00000000f4000000)  // alloc1 2M
  to   space 10240K,   0% used [0x00000000f4000000, 0x00000000f4000000, 0x00000000f4a00000)
 tenured generation   total 102400K, used 51200K [0x00000000f4a00000, 0x00000000fae00000, 0x00000000fae00000) // alloc2 50M
   the space 102400K,  50% used [0x00000000f4a00000, 0x00000000f7c00010, 0x00000000f7c00200, 0x00000000fae00000)
 compacting perm gen  total 21248K, used 2580K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
   the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb085178, 0x00000000fb085200, 0x00000000fc2c0000)
No shared spaces configured.

*/