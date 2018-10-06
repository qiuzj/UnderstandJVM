package jvm.deep.disassemble.section16.jit;

public class HashCode {
	public static int hash(Object in) {
		if (in instanceof Exception) {
			return System.identityHashCode(in);
		} else {
			return in.hashCode();
		}
	}
}
