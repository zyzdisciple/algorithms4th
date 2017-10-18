package zyx.suanfa.unitone;

public class SomeInterestingMethod {

	/**
	 * 乘法的另类定义计算方式, 如果将 + 换成 * 并将 return 0 换成 return 1 则是求 a 的 b 次方; 忽略b的正负号;
	 * @param a
	 * @param b
	 * @return
	 */
	public static int e18(int a, int b) {
		if (b == 0 )
			return 0;
		if (b % 2 == 0)
			return e18(a + a, b / 2);
		return e18(a + a, b / 2) + a;
	}
}
