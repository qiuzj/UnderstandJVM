package jvm.specification.se8.chapter3;

public class ClassInstance {
	Object create() {
		return new Object();
	}
}
/*
1 new #1
2 dup
3 invokespecial #2
4 areturn
*/