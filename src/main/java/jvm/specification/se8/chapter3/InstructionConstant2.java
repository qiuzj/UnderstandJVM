package jvm.specification.se8.chapter3;

public class InstructionConstant2 {

	void spin() {
		int i;
		for (i = 0; i < 100; ++i) {
			; // Loop body is empty
		}
	}
	
	void dspin() {
		double i;
		for (i = 0.0; i < 100.0; ++i) {
			; // Loop body is empty
		}
	}
	
}
