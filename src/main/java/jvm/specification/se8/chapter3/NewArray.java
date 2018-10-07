package jvm.specification.se8.chapter3;

public class NewArray {
	void createBuffer() {
		int buffer[];
		int bufsz = 100;
		int value = 12;
		buffer = new int[bufsz];
		buffer[10] = value;
		value = buffer[11];
	}
}
