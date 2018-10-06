package jvm.specification.se8.chapter3;

/**
 * 运行时常量池
 * 
 * @author 二进制之路
 *
 */
public class RuntimeConstPool {
	void useManyNumeric() {
		int i = 100;
		int j = 1000000;
		long l1 = 1;
		long l2 = 0xffffffff;
		double d = 2.2;
		// ...do some calculatoins...
	}
}
/*
0 bipush 100
1 istore_1
2 ldc #1
3 istore_2
4 lconst_1
5 lstore_3
6 ldc2_w #2
7 lstore_4
8 ldc2_w #3
9 lstore_5
*/