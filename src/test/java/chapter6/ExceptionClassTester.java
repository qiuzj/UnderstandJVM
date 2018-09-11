package chapter6;

import org.junit.Test;

public class ExceptionClassTester {

	@Test
	public void test1() {
		System.out.println("result=" + new ExceptionClass().inc());
	}
	
	@Test
	public void test2() {
		System.out.println("result=" + new ExceptionClass2().inc());
	}
	
}
