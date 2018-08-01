package cn.javaee.javassist.insert_before_method;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class Test2 {
	@Test
	public void test() throws Throwable {
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.get("cn.javaee.javassist.insert_before_method.Hello");
		CtMethod m = cc.getDeclaredMethod("sayWhat");
//		m.insertBefore("{ System.out.println($1); System.out.println($2); }");
//		m.insertBefore("{ System.out.println($args[0]); System.out.println($args[1]); }");
		m.insertBefore("{ sayWhat2($$); }");
		cc.writeFile();
		Class c = cc.toClass(); //
		Hello h = (Hello) c.newInstance(); //
		h.sayWhat("qiuzj", 34);
		
//		System.out.println(Thread.currentThread().getContextClassLoader());
//		System.out.println(c.getClassLoader());
	}
}