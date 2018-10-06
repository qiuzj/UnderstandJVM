package jvm.specification.se8.chapter3;

class Near {
	int it;
	public int getItNear() {
		return getIt();
	}
	private int getIt() {
		return it;
	}
}
/*
1 aload_0
2 invokespecial #1
4 ireturn

1 aload_0
2 getfield #2
3 ireturn
*/