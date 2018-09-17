package jvm.specification.se8.chapter3;

public class InstructionConstant {

	void spin() {
		int i;
		for (i = 0; i < 100; i++) {
			; // Loop body is empty
		}
	}
	
	void dspin() {
		double i;
		for (i = 0.0; i < 100.0; i++) {
			; // Loop body is empty
		}
	}
	
	double doubleLocals(double d1, double d2) {
		return d1 + d2;
	}
	
	void sspin() {
		short i;
		for (i = 0; i < 100; i++) {
			; // Loop body is empty
		}
	}
	
}
