public class IEEETester {

	public static void main(String[] args) {
		testFloat(1111111.59112222222222f, 111111111111.333333333333f);
		testFloat(1111111.59112222222222f, 222222222222.333333333333f);
		testFloat(1.2222224622f, 2.333334533333f);
		testFloat(331.2222222f, 442.333333f);
		byte b = 1;
		char c = 2;
		short s = 3;
//		c = s;
//		s = c;
		System.out.println(Float.MIN_NORMAL + " " + Float.MIN_VALUE + " " + Float.MAX_VALUE + " " + Float.MAX_EXPONENT);
		System.out.println(Long.MIN_VALUE + " " + Long.MAX_VALUE);
		float f = -1;
		System.out.println(f + " " + (Float.MIN_VALUE < f));
	}
	
	public static void testFloat(float a, float b) {
		float c = a + b;
		System.out.println(String.format("%s+%s=%s", a, b, c));
	}
	
}
