package jvm.specification.se8.chapter3;

public class MyObj {
	int i;
	MyObj example() {
		MyObj o = new MyObj();
		return silly(o);
	}
	MyObj silly(MyObj o) {
		if (o != null) {
			return o;
		} else {
			return o;
		}
	}
}
