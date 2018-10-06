package jvm.deep.disassemble.section15.grammar_compiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class VIPOnlyMerchantTester {

	@Test
	public void test2() {
		VIPOnlyMerchant vm = new VIPOnlyMerchant();
		VIP v = new VIP();
		vm.actionPrice(v);
	}
	
	@Test
	public void test1() {
		Customer c = new VIP();
		VIPOnlyMerchant vm = new VIPOnlyMerchant();
//		vm.actionPrice(c); // try again
		VIP v = new VIP();
		System.out.println(vm.actionPrice(v));
		
		Merchant<Customer> mc = new Merchant<>();
		mc.actionPrice(c);
		mc.actionPrice(v);
		
		try {
			Method m = VIPOnlyMerchant.class.getDeclaredMethod("actionPrice", new Class[]{Customer.class});
			System.out.println(m.invoke(vm, c));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
