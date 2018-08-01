package cn.javaee.javassist.insert_before_method;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class TestA {
	@Test
	public void test() throws Throwable {
//		Hello h1 = new Hello(); // attempted  duplicate class definition for name: "cn/javaee/javassist/insert_before_method/Hello"
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.get("cn.javaee.javassist.insert_before_method.Hello");
		CtMethod m = cc.getDeclaredMethod("say");
		m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
		cc.writeFile();
		cc.defrost();
		m.insertBefore("{ System.out.println(\"Hello.say()1:\"); }");
		cc.writeFile();
		Class c = cc.toClass(); //
		Hello h = (Hello) c.newInstance(); //
		h.say();
		h.say("ok");
		
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(c.getClassLoader());
	}
	
//	public static void main(String[] args) {
//		Hello h = new Hello();
//		h.say();
//	}
}