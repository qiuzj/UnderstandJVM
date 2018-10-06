package jvm.deep.disassemble.section15.grammar_compiler;

class GenericTest<T extends Number> {
	T foo(T t) {
		return t;
	}
}