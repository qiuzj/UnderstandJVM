package jvm.specification.se8.chapter3;

public class AcceptParams {

	int addTwo(int i, int j) {
		return i + j;
	}

	static int addTwoStatic(int i, int j) {
		return i + j;
	}
	
}
/*
1 iload_1
2 iload_2
3 iadd
4 ireturn

1 iload_0
2 iload_1
3 iadd
4 ireturn
*/
