package jvm.deep.disassemble.section15.grammar_compiler;

/**
 * <pre>
 * <? extends T>：是指 “上界通配符（Upper Bounds Wildcards）”
 * <? super T>：是指 “下界通配符（Lower Bounds Wildcards）”
 * </pre>
 * 
 * @author 二进制之路
 *
 */
class PlateTester {
	public static void main(String[] args) {
//		Plate<Fruit> p = new Plate<Apple>(new Apple()); // try again. 就算容器里装的东西之间有继承关系，但容器之间是没有继承关系的。
		Plate<? extends Fruit> p1 = new Plate<Fruit>(new Apple());
//		Plate<? extends Fruit> p2 = new Plate<Apple>(new Apple()); // <=Fruit

//		Plate<? super Fruit> p3 = new Plate<Apple>(new Apple()); // try again
//		Plate<? super Fruit> p4 = new Plate<Fruit>(new Apple()); // >=Fruit
//		Plate<? super Fruit> p5 = new Plate<Food>(new Apple());
//		Plate<? super Fruit> p6 = new Plate<Thing>(new Apple());
	}
}