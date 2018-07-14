package chapter3;

public class TT {

	private static TT tt;

	public static void main(String[] args) throws InterruptedException {
		tt = new TT();
		tt = null;
		System.gc();
		Thread.sleep(1);
		if (tt == null) {
			System.out.println("Oh no");
		} else {
			System.out.println("I'm still alive");
		}

		tt = null;
		System.gc();
		Thread.sleep(1);
		if (tt == null) {
			System.out.println("2 Oh no");
		} else {
			System.out.println("2 I'm still alive");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("I'm finalize");
		tt = this;
	}
	
}
