package chapter6;

/**
 * 用于Class文件格式分析
 * 
 * @author 二进制之路
 *
 */
public class ExceptionClass2 {

	public int inc() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			System.out.println("Exception x=" + x);
			return x;
		} finally {
			x = 3;
			System.out.println("finally x=" + x);
		}
	}
}
