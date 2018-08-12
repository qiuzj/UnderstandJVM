package chapter9.javassistproxy2;

public class Hello implements IHello2 {
	@Override
	public void sayHello() {
		System.out.println("hello world");
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
	}

	@Override
	public String sayHello2(int age) {
		System.out.println("your age is " + age);
		return "age" + age;
	}

	@Override
	public int sayHello3(String a, boolean b, Object c) {
		String abc = a + b + c;
		System.out.println("a + b + c=" + abc);
		return abc.hashCode();
	}
}