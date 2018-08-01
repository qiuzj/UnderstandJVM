package javassist.tools.reflect;

import org.junit.Test;

/**
 * @author Brett Randall
 */
public class ClassMetaobjectTest {
	@Test
	public void test() throws Throwable {
		Loader loader = new Loader();
		loader.makeReflective("javassist.tools.reflect.Person", 
				"javassist.tools.reflect.Metaobject",
				"javassist.tools.reflect.ClassMetaobject");
		loader.run("javassist.tools.reflect.Person", new String[] {}); // Loads a class and calls main() in that class.
	}
}
