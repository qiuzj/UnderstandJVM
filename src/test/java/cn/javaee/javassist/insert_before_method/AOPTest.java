package cn.javaee.javassist.insert_before_method;

import org.junit.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class AOPTest {

	@Test
	public void test1() {
		Hello hello = new Hello();
		hello.say();
		hello.say("ookk");
		hello.sayWhat("name",26);
		hello.sayWhat2("name", 26);
	}

	@Test
	public void test2() throws Throwable {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("cn.javaee.javassist.insert_before_method.Hello");
        for (CtMethod cm : cc.getDeclaredMethods()) {
        	cm.instrument(new ExprEditor() {
        		@Override
        		public void edit(MethodCall m) throws CannotCompileException {
        			m.replace("{"
        					+ "long startTime = System.currentTimeMillis();"
        					+ "$_=$proceed($$);"
        					+ "System.out.println((System.currentTimeMillis() - startTime));"
        					+ "}");
        		}
        	});
        }
        
		Hello hello = (Hello) cc.toClass().newInstance();
		hello.say();
		hello.say("ookk");
		hello.sayWhat("name",26);
//		hello.sayWhat2("name", 26);
	}
	
}
