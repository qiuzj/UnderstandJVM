package jvm.specification.se8.chapter3;

public class Arithmetic {

	int align2agrain(int i, int grain) {
		return ((i + grain - 1) & ~(grain - 1));
	}
	
}
