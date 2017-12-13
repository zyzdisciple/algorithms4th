package zyx.algorithms4th.unittwo;

import java.util.Date;

public class SortCompare {

	private Comparable<Double>[] array;
	private int N;
	
	public SortCompare() {
		N = 1;
		generateArray();
		
	}
	
	public SortCompare(int length) {
		N = length;
		generateArray();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SortCompare(Comparable[] array) {
		this.array = array;
		N = array.length;
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
	
	//为了速度测试对比
	private void generateArray(int length) {
		
		N = length;
		generateArray();
	}
	
	//在排序顺序不会出错的基础上, 调用这个方法, 进行对比
	public void time(AbstractSort<Double> sort, int times) {
		
		int length = 1024;
		long start, end;
		for(int i = 0; i < times; i++) {
			length = length << 1;
			generateArray(length);
			sort.setArray(array);
			start = new Date().getTime();
			sort.sort();
			end = new Date().getTime();
			System.out.println("count: " + i + " secondes: " + (end - start) + " length: " + length);
		}
	}
	
}
