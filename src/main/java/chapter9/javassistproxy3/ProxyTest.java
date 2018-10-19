package chapter9.javassistproxy3;

import chapter9.CustomHandler;

public class ProxyTest {

	public static void main(String[] args) throws Throwable {
		IHello hello = new Hello();
		CustomHandler customHandler = new CustomHandler(hello);
		IHello helloProxy = (IHello) ProxyFactory.newProxyInstance(hello.getClass().getClassLoader(), IHello.class, customHandler);
		System.out.println();
		System.out.println("a+false+Object=" + helloProxy.sayHello3("a", false, new Object()));
	}
	
}
