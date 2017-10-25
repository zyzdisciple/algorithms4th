package zyx.algorithms4th.unitone;

public class SomeInterestingMethod {

	/**
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
