package jvm.specification.se8.chapter3;

/**
 * 算术运算
 * 
 * @author 二进制之路
 *
 */
public class Arithmetic33 {

	int align2agrain(int i, int grain) {
		return ((i + grain - 1) & ~(grain - 1));
	}
	
}
