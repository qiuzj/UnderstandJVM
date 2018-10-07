package jvm.specification.se8.chapter3;

public class ANewArray {
	void createThreadArray() {
		Thread threads[];
		int count = 10;
		threads = new Thread[count];
		threads[0] = new Thread();
	}
}
