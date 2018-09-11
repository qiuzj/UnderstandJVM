package chapter6;

/**
 * 用于Class文件格式分析
 * 
 * @author 二进制之路
 *
 */
public class ExceptionClass {
	public int inc() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}
	}
}
