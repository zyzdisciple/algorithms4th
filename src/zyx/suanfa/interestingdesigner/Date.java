package zyx.suanfa.interestingdesigner;

public class Date {

	private final int value;
	/**
	 *  31 % 32 = 31
	 *  366 % 512 = 366
	 * @param m
	 * @param y
	 * @param d
	 */
	public Date(int m, int y, int d) {
		value = y * 512 + m * 32 + d;
	}
	
	/**
	 *  除32, (k*16*y + m) % 16 = m
	 * @return
	 */
	public int month() {
		return (value / 32) % 16;
	}
	
	public int year() {
		return value % 512;
	}
	
	public int day() {
		return value % 32;
	}
}
