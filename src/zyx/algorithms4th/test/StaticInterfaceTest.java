package zyx.algorithms4th.test;

public interface StaticInterfaceTest {

	public static void test1() {
		System.out.println("stiatic 输出");
	}
	
	public default void testDefault() {
		System.out.println("default输出");
	}
}
