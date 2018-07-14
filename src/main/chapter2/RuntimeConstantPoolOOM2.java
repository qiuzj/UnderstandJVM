package chapter2;

public class RuntimeConstantPoolOOM2 {

	public static void main(String[] args) {
		String str1 = new StringBuilder("中国").append("钓鱼岛").toString();
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);

		String str3 = new StringBuilder("qiu").append("zhanjia").toString();
		System.out.println(str3.intern() == str3);

		String str4 = new StringBuilder("b").append("yte").toString();
		System.out.println(str4.intern() == str4);
		
		// ---
		String str5 = "qiuzhanjia";
		System.out.println(str5.intern() == str3);
		
		String str6 = new StringBuilder("qiu").append("zhanjia").toString();
		System.out.println(str6.intern() == str6);
	}
}
/*
JDK 1.6：
false
false
false
false

JDK 1.7、1.8：
true
false
true
false
*/