package zyx.algorithms4th.unittwo;

import java.util.Random;

public class Test {
	
	private static UnitTwoPonitOneExercises ut = new UnitTwoPonitOneExercises();
	private static UnitTwoPointThreeExercises ut3 = new UnitTwoPointThreeExercises();

	public static void main(String[] args) {
		//testSelectSort();
		//testInsertSort();
		//testShellSort();
		//testMergeSort();
		//testMergeSortImprove();
		testQuickSort();
		//sort2124();
		//sort2125();
		//sort235();
	}
	
	public static void testSelectSort() {
		SortCompare sc = new SortCompare(10000);
		SelectSort<Double> ss = new SelectSort<Double>();
		System.out.println(sc.time(ss));
	}
	
	public static void testInsertSort() {
		SortCompare sc = new SortCompare(10000);
		InsertSort<Double> ss = new InsertSort<Double>();
		System.out.println(sc.time(ss));
	}
	
	public static void testShellSort() {
		SortCompare sc = new SortCompare(100000);
		ShellSort<Double> ss = new ShellSort<Double>();
		System.out.println(sc.time(ss));
	}
	
	public static void testMergeSort() {
		SortCompare sc = new SortCompare(50000);
		MergeSort<Double> ss = new MergeSort<>();
		System.out.println(sc.time(ss));
	}
	
	public static void testMergeSortImprove() {
		SortCompare sc = new SortCompare(100000);
		MergeSortImprove<Double> ss = new MergeSortImprove<>();
		System.out.println(sc.time(ss));
	}
	
	public static void testQuickSort() {
		SortCompare sc = new SortCompare(10000);
		QuickSort<Double> ss = new QuickSort<>();
		System.out.println(sc.time(ss));
//		System.out.println("count0: " + ss.getCount0());
//		System.out.println("count1: " + ss.getCount1());
//		System.out.println("count2: " + ss.getCount2());
	}
	
	public static void sort2124() {
		SortCompare sc = new SortCompare(10000);
		UnitTwoPonitOneExercises.Sort2124<Double> ss = ut.new Sort2124<>();
		System.out.println(sc.time(ss));
	}
	
	public static void sort2125() {
		SortCompare sc = new SortCompare(10000);
		UnitTwoPonitOneExercises.Sort2125<Double> ss = ut.new Sort2125<>();
		System.out.println(sc.time(ss));
	}
	
	@SuppressWarnings("unchecked")
	public static void sort235() {
		Comparable<Integer>[] array = new Comparable[1000000];
		Random r = new Random();
		for (int i = 0, length = array.length; i < length; i++) {
			array[i] = r.nextInt(2);
		}
		SortCompare sc = new SortCompare(array);
		UnitTwoPointThreeExercises.Sort235<Double> ss = ut3.new Sort235<>();
		System.out.println(sc.time(ss));
	}
	
	@org.junit.Test
	public void testInsertSort2() {
		
		Integer[] array = {2,34,6,1,3,56,3};
		InsertSort.sort(array, 1, 7);
		for (int i: array) {
			System.out.println(i);
		}
	}
	
	
}
