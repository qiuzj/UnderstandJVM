package jvm.specification.se8.chapter3;

public class MethodInvoke2 {

	int add12and13() {
		return addTwoStatic(12, 13);
	}
	
	static int addTwoStatic(int i, int j) {
		return i + j;
	}
}
/*
1 bipush 12
2 bipush 13
3 invokestatic #1
4 ireturn
*/