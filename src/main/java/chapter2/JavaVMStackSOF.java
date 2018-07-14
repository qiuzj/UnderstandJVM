package chapter2;
/**
 * <pre>
 * 栈溢出
 * VM Args：-Xss128k
 * </pre>
 * 
 * @author zzm
 */
public class JavaVMStackSOF {

	private int stackLength = 1;

	public void stackLeak() { // 太深
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}
	}
}
/*
stack length:1000Exception in thread "main" 
java.lang.StackOverflowError
	at chapter2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:11)
	at chapter2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
	at chapter2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
	at chapter2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
	at chapter2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
*/