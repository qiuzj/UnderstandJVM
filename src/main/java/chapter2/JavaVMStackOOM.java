package chapter2;

/**
 * VM Args：-Xss2M （这时候不妨设大些，可能会导致操作系统假死）
 * 
 * @author zzm
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while (true) {
		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) throws Throwable {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
/*
Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
*/
