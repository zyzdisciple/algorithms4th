package zyx.algorithms4th.unittwo;

import java.util.Date;

public class SortCompare {

	private Comparable<Double>[] array;
	private final int N;
	
	public SortCompare(int length) {
		generateArray();
		N = length;
	}
	
	public double time(AbstractSort<Double> sort) {
		long start = new Date().getTime();
		sort.sort(array);
		long end = new Date().getTime();
		return end - start;
	}
	
	private void generateArray() {
		for (int i = 0; i < N; i++) {
			array[i] = (Comparable<Double>) Math.random();
		}
	}
}
