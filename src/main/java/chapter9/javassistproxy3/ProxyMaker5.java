package chapter9.javassistproxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtPrimitiveType;
import javassist.bytecode.AccessFlag;

public class ProxyMaker5 {
	
	public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws Throwable {
		ClassPool pool = ClassPool.getDefault();
		
		// 1.创建代理类：public class NewProxyClass
		CtClass proxyCc = pool.makeClass("NewProxyClass");

		/* 2.给代理类添加字段：private InvocationHandler h; */
		CtClass handlerCc = pool.get(InvocationHandler.class.getName());
		CtField handlerField = new CtField(handlerCc, "h", proxyCc);
		handlerField.setModifiers(AccessFlag.PRIVATE);
		proxyCc.addField(handlerField);

		/* 3.添加构造函数：public NewProxyClass(InvocationHandler paramInvocationHandler) { this.h = paramInvocationHandler; } */
		CtConstructor ctConstructor = new CtConstructor(new CtClass[] { handlerCc }, proxyCc);
		ctConstructor.setBody("$0.h = $1;"); // $0代表this, $1代表构造函数的第1个参数
		proxyCc.addConstructor(ctConstructor);
		
		String staticBody = "";
		
		/* 4.遍历接口，为代理类添加相应接口方法及实现 */
		for (Class<?> interfaceClass : interfaces) {
			CtClass interfaceCc = pool.get(interfaceClass.getName());
			
			// 4.1 为代理类添加接口：public class NewProxyClass implements IHello
			proxyCc.addInterface(interfaceCc);
			
			/* 4.2 获取接口的反射方法、javassist方法列表 */
			Method[] methods = interfaceClass.getDeclaredMethods();
			CtMethod[] ctMethods = interfaceCc.getDeclaredMethods();
			
			// 4.3 为代理类添加相应方法及实现
			for (int i = 0; i < ctMethods.length; i++) {
				// 4.3.1 为代理类添加反射方法字段, 如private Method m0;
				String methodFieldName = "m" + i; // 新的方法名
				CtField methodField = new CtField(pool.get("java.lang.reflect.Method"), methodFieldName, proxyCc);
				methodField.setModifiers(AccessFlag.PRIVATE | AccessFlag.STATIC);
				proxyCc.addField(methodField);
				
				String classStr = "";
				if (methods[i].getParameterTypes().length > 0) {
					for (Class<?> clazz : methods[i].getParameterTypes()) {
						classStr += ((classStr == "") ? clazz.getName() : "," + clazz.getName()) + ".class";
					}
					classStr = "new Class[] {" + classStr + "}";
				} else {
					classStr = "new Class[0]";
				}
				System.out.println("classStr: " + classStr);
				staticBody += methodFieldName + "=Class.forName(\"" + interfaceClass.getName() + "\").getDeclaredMethod(\"" + ctMethods[i].getName() + "\", " + classStr + ");";
				
				String tpl = "%s=Class.forName(\"%s\").getDeclaredMethod(\"%s\", %s);";
				
				/* 4.3.2 为方法添加方法体 */
				String methodBody = "$0.h.invoke($0, " + methodFieldName + ", $args)";
				// 如果有返回类型，则需要转换为相应类型后返回
				if (CtPrimitiveType.voidType != ctMethods[i].getReturnType()) {
					// 对8个基本类型进行转型
					// 例如：((Integer)this.h.invoke(this, this.m2, new Object[] { paramString, new Boolean(paramBoolean), paramObject })).intValue();
					if (ctMethods[i].getReturnType() instanceof CtPrimitiveType) {
						CtPrimitiveType ctPrimitiveType = (CtPrimitiveType) ctMethods[i].getReturnType();
						methodBody = "return ((" + ctPrimitiveType.getWrapperName() + ") " + methodBody + ")." + ctPrimitiveType.getGetMethodName() + "()";
					} else { // 对于非基本类型直接转型即可
						methodBody = "return (" + ctMethods[i].getReturnType().getName() + ") " + methodBody;
					}
				}
				methodBody += ";";
				CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
						ctMethods[i].getParameterTypes(), proxyCc);
				newMethod.setBody(methodBody);
				proxyCc.addMethod(newMethod);
				
				System.out.println("Invoke method: " + methodBody);
			}
		}
		
		proxyCc.makeClassInitializer().setBody("{" + staticBody + "}");
		proxyCc.writeFile("D:/曲库");
		
		// 5.生成代理实例. 将入参InvocationHandler h设置到代理类的InvocationHandler h变量
		@SuppressWarnings("unchecked")
		Object proxy = proxyCc.toClass().getConstructor(InvocationHandler.class).newInstance(h);

		// 6.将所原接口的所有反射方法设置到代理类的相应字段

		return proxy;
	}

}
