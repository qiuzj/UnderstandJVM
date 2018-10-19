package jvm.specification.se8.chapter3;

public class NextIndex {
	private long index = 0;
	public long nextIndex() {
		return index++;
	}
}
/*
0 aload_0
1 dup
2 getfield #1
5 dup2_x1
6 lconst_1
7 ladd
8 putfield #2
11 lreturn
*/