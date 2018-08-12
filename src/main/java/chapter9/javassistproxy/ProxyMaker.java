package chapter9.javassistproxy;

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
import javassist.CtPrimitiveType;

public class ProxyMaker {
	
	public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws Throwable {
		ClassPool pool = ClassPool.getDefault();
		// 1.创建代理类
		CtClass proxyCc = pool.makeClass("NewProxClass");

		/* 2.给代理类添加字段：InvocationHandler h; */
		CtClass handlerCc = pool.get(h.getClass().getName());
		CtField handlerField = new CtField(handlerCc, "h", proxyCc);
		proxyCc.addField(handlerField);
		
		Map<String, Method> methodFieldMap = new HashMap<>();
		int methodFieldIndex = 0;
		
		/* 3.遍历接口，为代理类添加相应接口方法及实现 */
		for (Class<?> interfaceClass : interfaces) {
			CtClass interfaceCc = pool.get(interfaceClass.getName());
			// 3.1 为代理类添加接口：implements interfaceName
			proxyCc.addInterface(interfaceCc);
			
			/* 3.2 获取接口的反射方法、javassist方法列表 */
			CtMethod[] ctMethods = interfaceCc.getDeclaredMethods();
			Method[] methods = interfaceClass.getDeclaredMethods();
			
			// 3.3 为代理类添加相应方法及实现
			for (int i = 0; i < ctMethods.length; i++) {
				// 3.3.1 为代理类添加反射方法字段, 如Method m1;
				String methodFieldName = "m" + methodFieldIndex++; // 新的方法名
				CtField methodField = new CtField(pool.get("java.lang.reflect.Method"), methodFieldName, proxyCc);
				proxyCc.addField(methodField);
				methodFieldMap.put(methodFieldName, methods[i]); // 将反射方法暂存起来, 如<m1, Method>，Method代表sayHello
				
				// 3.3.2 为方法添加方法体
				String body = "$0.h.invoke($0, " + methodFieldName + ", $args)";
				if (CtPrimitiveType.voidType != ctMethods[i].getReturnType()) {
					if (ctMethods[i].getReturnType() instanceof CtPrimitiveType) {
						CtPrimitiveType ctPrimitiveType = (CtPrimitiveType) ctMethods[i].getReturnType();
						body = "return ((" + ctPrimitiveType.getWrapperName() + ") " + body + ")." + ctPrimitiveType.getGetMethodName() + "()";
					} else {
						body = "return (" + ctMethods[i].getReturnType().getName() + ") " + body;
					}
				}
				body += ";";
				System.out.println("Invoke method: " + body);
				CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
						ctMethods[i].getParameterTypes(), proxyCc);
				newMethod.setBody(body);
				proxyCc.addMethod(newMethod);
			}
		}
		
		// 4.生成代理实例
		Object proxy = proxyCc.toClass().newInstance();
		
		// 5.将入参InvocationHandler h设置到代理类的InvocationHandler h变量
		Field field = proxy.getClass().getDeclaredField("h");
		field.setAccessible(true);
		field.set(proxy, h);

		// 6.将所原接口的所有反射方法设置到代理类的相应字段
		for (Entry<String, Method> entry : methodFieldMap.entrySet()) {
			field = proxy.getClass().getDeclaredField(entry.getKey());
			field.setAccessible(true);
			field.set(proxy, entry.getValue());
		}

		return proxy;
	}

}
