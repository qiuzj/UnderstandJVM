package jvm.specification.se8.chapter3;

class Far extends Near{
	int getItFar() {
		return super.getItNear();
	}
}
/*
1 aload_0
2 invokespecial #1
3 ireturn
*/