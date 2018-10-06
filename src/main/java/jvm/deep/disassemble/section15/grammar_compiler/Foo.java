package jvm.deep.disassemble.section15.grammar_compiler;

import java.util.ArrayList;

public class Foo {
	public int foo() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0); // 自动装箱
		int result = list.get(0); // 强制转型
		return result;
	}
}
