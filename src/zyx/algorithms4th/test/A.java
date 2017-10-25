package zyx.algorithms4th.test;

public class A {

	private int a;
	
	public void increment() {
		a++;
	}
	
	public void change(A a) {
		this.a = a.a;
	}
	
	public A(int a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "A [a=" + a + "]";
	}
	
	public int getValue() {
		return a;
	}
}
