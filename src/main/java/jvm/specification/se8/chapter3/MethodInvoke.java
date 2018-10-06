package jvm.specification.se8.chapter3;

public class MethodInvoke {

	int add12and13() {
		return addTwo(12, 13);
	}
	
	int addTwo(int i, int j) {
		return i + j;
	}
}
/*
1 aload_0
2 bipush 12
3 bipush 13
4 invokevirtual #1
5 ireturn
*/