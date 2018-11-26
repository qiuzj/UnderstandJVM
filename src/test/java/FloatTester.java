
public class FloatTester {

	public static void main(String[] args) {
		System.out.println(String.format("能表示的最小值：%s，规格化最小值：%s，能表示的最大值：%s",
				Float.MIN_VALUE, Float.MIN_NORMAL, Float.MAX_VALUE));
		
		System.out.println(String.format("指数范围：[%s,%s]",Float.MIN_EXPONENT, Float.MAX_EXPONENT));
		
		float underflow = Float.MIN_VALUE - ((Double) Math.pow(2, -149)).floatValue();
		float overflow = Float.MAX_VALUE + ((Double) Math.pow(2, 103)).floatValue();
		System.out.println(String.format("下溢出：%s，上溢出：%s", underflow, overflow));
		
//		System.out.println(String.format("%s,%s",Float.NaN,Float.POSITIVE_INFINITY));
		
//		System.out.println((float)Math.pow(2, -23) + " " + Math.pow(2, 104));
//		System.out.println(((Double) Math.pow(2, -149)).floatValue());
	}
	
}
