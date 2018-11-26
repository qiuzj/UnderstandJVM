import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * javac OutOfMemoryError.java
 * java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError OutOfMemoryError
 * java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/oom OutOfMemoryError
 * java -Xms20m -Xmx20m -XX:+HeapDumpOnCtrlBreak OutOfMemoryError： JDK 8 - Unrecognized VM option 'HeapDumpOnCtrlBreak'
 * <p>
 * 
 * @author 二进制之路
 *
 */
public class OutOfMemoryError {
	public static void main(String[] args) {
		List<String> resultList = new ArrayList<>();
		while (true) {
			resultList.add("OutOfMemoryError soon");
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
