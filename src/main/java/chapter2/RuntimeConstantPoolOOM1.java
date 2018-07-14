package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 * 
 * @author zzm
 */
public class RuntimeConstantPoolOOM1 {

	public static void main(String[] args) {
		// 使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
/*
JDK 1.6：运行时常量池属于方法区
Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
at java.lang.String.intern(Native Method)
at chapter2.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:19)
JDK 1.7：不抛异常
*/