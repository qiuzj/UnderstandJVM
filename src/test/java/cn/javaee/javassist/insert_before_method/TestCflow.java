package cn.javaee.javassist.insert_before_method;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class TestCflow {
	@Test
	public void test() throws Throwable {
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.get("cn.javaee.javassist.insert_before_method.Hello");
		CtMethod m = cc.getDeclaredMethod("fact");
		m.useCflow("fact");
		m.insertBefore("System.out.println($cflow(fact));"
				+ "if ($cflow(fact) == 0)"
	              + "    System.out.println(\"fact \" + $1);");
//		m.insertAfter("$_=($r)say();"); // javassist.CannotCompileException: [source error] invalid type for $r
		cc.writeFile();
		Class c = cc.toClass(); //
		Hello h = (Hello) c.newInstance(); //
		h.fact(5);
	}
}