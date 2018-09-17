package jvm.deep.disassemble.section2.basetype;

public class Foo {
	public static void main(String[] args) {
		boolean flag = true; // boolean flag = 2; 直接编译的话 javac 会报错
		if (flag)
			System.out.println("Hello, Java!");
		if (flag == true)
			System.out.println("Hello, JVM!");
	}
}