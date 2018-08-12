package chapter9;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

public class ProxyMaker2 {

	public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws Throwable {
		ClassPool pool = ClassPool.getDefault();
		CtClass proxyCc = pool.makeClass("NewProxClass");

		CtClass handlerCc = pool.get(h.getClass().getName());
		CtField handlerField = new CtField(handlerCc, "h", proxyCc);
		proxyCc.addField(handlerField);
		
		Map<String, Method> methodFieldMap = new HashMap<>();
		int methodFieldIndex = 0;
		for (Class<?> clazz : interfaces) {
			CtClass interfaceCc = pool.get(clazz.getName());
			proxyCc.addInterface(interfaceCc);
			
			CtMethod[] ctMethods = interfaceCc.getDeclaredMethods();
			Method[] methods = clazz.getDeclaredMethods();
			
			for (int i = 0; i < ctMethods.length; i++) {
				CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
						ctMethods[i].getParameterTypes(), proxyCc);
//				
//				CtMethod handlerMethod = handlerCc.getDeclaredMethod("invoke");
//				newMethod.setWrappedBody(handlerMethod, null);
//				newMethod.setBody(handlerMethod, null);method.instrument(editor);
//				this.h.invoke(this, m3, null);?
//				newMethod.setBody("h.invoke($0, null, $args);");
//				System.out.println(method.getName());
				
				String methodFieldName = "m" + methodFieldIndex++;
				CtField methodField = new CtField(pool.get("java.lang.reflect.Method"), methodFieldName, proxyCc);
				proxyCc.addField(methodField);
				
				methodFieldMap.put(methodFieldName, methods[i]);
				
//				Field field = proxyCc.getClass().getDeclaredField(methodName);
//				field.setAccessible(true);
//				field.set(proxy, h);

				newMethod.setBody("h.invoke($0, " + methodFieldName + ", $args);");

//				newMethod.setBody("h.invoke($0, $0.getClass().getDeclaredMethod(\"" + method.getName() + "\", $sig), $args);");
				
//				clazz.getDeclaredMethod(name, parameterTypes)
//				newMethod.setBody("System.out.println($0);");
//				newMethod.setBody(method, null);
//				CtClass handlerCc = pool.get(h.getClass().getName());
//				CtMethod handlerMethod = handlerCc.getDeclaredMethod("invoke");
				
//				newMethod.setBody(handlerMethod, null);
//				h.invoke(proxy, method, args);h.invoke(proxy, method, args);
				proxyCc.addMethod(newMethod);
			}
		}
		
		Object proxy = proxyCc.toClass().newInstance();
		
		Field field = proxy.getClass().getDeclaredField("h");
		field.setAccessible(true);
		field.set(proxy, h);

		for (Entry<String, Method> entry : methodFieldMap.entrySet()) {
			field = proxy.getClass().getDeclaredField(entry.getKey());
			field.setAccessible(true);
			field.set(proxy, entry.getValue());
		}
//		System.out.println(proxy.getClass().getDeclaredMethod("sayHello", null));
//		for (Method m : proxy.getClass().getDeclaredMethods()) {
////			h.invoke(proxy, m, m.getParameterTypes());
//			newMethod.setBody("$0.h.invoke(null, null, null);");
//		}
		return proxy;
	}

}
