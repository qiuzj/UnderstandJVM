package javassist.tools;

import static javassist.tools.Callback.insertAfter;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import junit.framework.TestCase;
import test.javassist.tools.DummyClass;

public class CallbackTest extends TestCase {

	@Test
    public void testSomeCallbacks() throws Exception {

        // Get class and method to change
        ClassPool pool = ClassPool.getDefault();
        CtClass classToChange = pool.get("test.javassist.tools.DummyClass");
        CtMethod methodToChange = classToChange.getDeclaredMethod("dummyMethod");

        // Insert after
        methodToChange.insertAfter(new Callback("Thread.currentThread(), dummyString") {
            @Override
            public void result(Object... objects) {
                assertEquals(objects[0], Thread.currentThread());
                assertEquals(objects[1], "dummyStringValue");
                System.out.println(String.format("1： %s %s", objects[0], objects[1]));
            }
        }.sourceCode());

        // Insert after using utility method
        insertAfter(methodToChange, new Callback("Thread.currentThread(), dummyString") {
            @Override
            public void result(Object... objects) {
                assertEquals(objects[0], Thread.currentThread());
                assertEquals(objects[1], "dummyStringValue");
                System.out.println(String.format("2： %s %s", objects[0], objects[1]));
            }
        });

        // Change class and invoke method;
        classToChange.toClass();
        new DummyClass().dummyMethod();
    }
}
