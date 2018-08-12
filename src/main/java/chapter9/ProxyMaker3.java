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
import javassist.CtPrimitiveType;

public class ProxyMaker3 {

	enum PrimitiveType {
		BOOLEAN("boolean", "Boolean", "booleanValue"),
		BYTE("byte", "Byte", "byteValue"),
		CHAR("char", "Character", "charValue"),
		SHORT("short", "Short", "shortValue"),
		INT("int", "Integer", "intValue"),
		LONG("long", "Long", "longValue"),
		FLOAT("float", "Float", "floatValue"),
		DOUBLE("double", "Double", "doubleValue");
		
		private String primitiveType;
		private String wrapperType;
		private String method;
		private PrimitiveType(String primitiveType, String wrapperType, String method) {
			this.primitiveType = primitiveType;
			this.wrapperType = wrapperType;
			this.method = method;
		}
		public String getPrimitiveType() {
			return primitiveType;
		}
		public String getWrapperType() {
			return wrapperType;
		}
		public String getMethod() {
			return method;
		}
		
		public static String getWrapperType(String primitiveType) {
			for (PrimitiveType type : PrimitiveType.values()) {
				if (type.getPrimitiveType().equals(primitiveType)) {
					return type.getWrapperType();
				}
			}
			return null;
//			throw new IllegalArgumentException("primitiveType not found: " + primitiveType);
		}
	}
	
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
				String methodFieldName = "m" + methodFieldIndex++;
				CtField methodField = new CtField(pool.get("java.lang.reflect.Method"), methodFieldName, proxyCc);
				proxyCc.addField(methodField);
				methodFieldMap.put(methodFieldName, methods[i]);
				
				CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
						ctMethods[i].getParameterTypes(), proxyCc);
				String body = "$0.h.invoke($0, " + methodFieldName + ", $args)";

				if (CtPrimitiveType.voidType != ctMethods[i].getReturnType()) {
//					String returnTypeName = ctMethods[i].getReturnType().getName();
//					String returnTypeWrapperName = PrimitiveType.getWrapperType(returnTypeName);
//					System.out.println(String.format("returnTypeName: %s, returnTypeWrapperName: %s", returnTypeName, returnTypeWrapperName));
					
					if (ctMethods[i].getReturnType() instanceof CtPrimitiveType) {
						CtPrimitiveType ctPrimitiveType = (CtPrimitiveType) ctMethods[i].getReturnType();
						body = "return ((" + ctPrimitiveType.getWrapperName() + ") " + body + ")." + ctPrimitiveType.getGetMethodName() + "()";
					} else {
						body = "return (" + ctMethods[i].getReturnType().getName() + ") " + body;
					}
					System.out.println("Invoke method: " + body);
				}
				newMethod.setBody(body + ";");
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

		return proxy;
	}

}
