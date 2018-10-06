package chapter6;

public class TypeConversion {

	public static void main(String[] args) {
		boolean bool = true;
		byte b = 127;
		short s = 32767;
		char c1 = 'a';
		char c2 = 97; // 范围：0~65535，不能小于0

		System.out.println(String.format("%s~%s", Byte.MIN_VALUE, Byte.MAX_VALUE));
		System.out.println(String.format("%s~%s", Short.MIN_VALUE, Short.MAX_VALUE));
		System.out.println(String.format("%s~%s", Character.MIN_VALUE, Character.MAX_VALUE) + " " + c2);
		
		int i;
		i = b;
		i = s;
		i = c1;
		i = c2;
		System.out.println(String.format("%s~%s", Integer.MIN_VALUE, Integer.MAX_VALUE));
		
		long l;
		l = b;
		l = s;
		l = c1;
		l = c2;
		l = i;
		System.out.println(String.format("%s~%s", Long.MIN_VALUE, Long.MAX_VALUE));

		float f;
		f = b;
		f = s;
		f = c1;
		f = c2;
		f = i;
		f = l;
		System.out.println(String.format("%s~%s", Float.MIN_VALUE, Float.MAX_VALUE));
		
		double d;
		d = b;
		d = s;
		d = c1;
		d = c2;
		d = i;
		d = l;
		d = f;
		System.out.println(String.format("%s~%s", Double.MIN_VALUE, Double.MAX_VALUE));
		
//		f = Long.MAX_VALUE;
//		System.out.println(f);
	}
	
}
