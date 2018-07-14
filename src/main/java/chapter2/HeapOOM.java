package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 堆溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpOnOutOfMemoryError：让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析
 * </pre>
 * 
 * @author zzm
 */
public class HeapOOM {

	static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();

		while (true) {
			list.add(new OOMObject());
		}
	}
}
/*
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid6648.hprof ...
Heap dump file created [27971896 bytes in 0.094 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at chapter2.HeapOOM.main(HeapOOM.java:19)
*/