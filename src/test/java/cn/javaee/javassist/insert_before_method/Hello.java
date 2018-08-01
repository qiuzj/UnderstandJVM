package cn.javaee.javassist.insert_before_method;

public class Hello {
	public void say() {
		System.out.println("Hello");
	}

	public void say(String ok) {
		System.out.println(ok);
	}
	
	public void sayWhat(String name, int age) {
		System.out.println("you say");
	}
	public void sayWhat2(String name, int age) {
		System.out.println("Copy your params：" + name + ", " + age);
	}
	
	int fact(int n) {
	    if (n <= 1)
	        return n;
	    else
	        return n * fact(n - 1);
	}
}
