package chapter9.javassistproxy3;

import chapter9.CustomHandler;

public class ProxyMain {

	public static void main(String[] args) throws Throwable {
		IHello hello = new Hello();
		CustomHandler customHandler = new CustomHandler(hello);
		long startTime = System.currentTimeMillis();
		IHello helloProxy = (IHello) ProxyFactory.newProxyInstance(hello.getClass().getClassLoader(), IHello.class, customHandler);
		System.out.println(System.currentTimeMillis() - startTime);
		
//		IHello helloProxy = new Hello();
		
		helloProxy.sayHello();
		
		System.out.println();
		System.out.println("My age=" + helloProxy.sayHello2(26));
		
		System.out.println();
		System.out.println("a+false+Object=" + helloProxy.sayHello3("a", false, new Object()));
	}
	
}
