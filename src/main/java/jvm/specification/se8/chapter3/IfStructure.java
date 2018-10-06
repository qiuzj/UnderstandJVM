package jvm.specification.se8.chapter3;

public class IfStructure {

	int lessThan100(double d) {
		if (d < 100.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	int greaterThan100(double d) {
		if (d > 100.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
}
