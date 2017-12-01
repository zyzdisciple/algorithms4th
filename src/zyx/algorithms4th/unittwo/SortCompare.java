package zyx.algorithms4th.unittwo;

import java.util.Date;

public class SortCompare {

	private Comparable<Double>[] array;
	private final int N;
	
	public SortCompare(int length) {
		N = length;
		generateArray();
	}
	
	public double time(AbstractSort<Double> sort) {
		sort.setArray(array);
		long start = new Date().getTime();
		sort.sort();
		long end = new Date().getTime();
		sort.show();
		return end - start;
	}
	
	private void generateArray() {
		array = new Double[N];
		for (int i = 0; i < N; i++) {
			array[i] = (Comparable<Double>) Math.random();
		}
	}
}
