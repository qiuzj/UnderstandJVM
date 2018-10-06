package jvm.specification.se8.chapter3;

public class NaN {

	public static void main(String[] args) {
		System.out.println(Float.NaN == Float.NaN);
		System.out.println(1 > Float.NaN);
		System.out.println(1 == Float.NaN);
		System.out.println(1 < Float.NaN);
		System.out.println(Float.NaN != Float.NaN);
		System.out.println(0 != Float.NaN);
	}
	
}
