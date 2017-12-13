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
		//testQuickSort();
		testQuickSortImprove();
		//testQuick3WaySort();
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
		SortCompare sc = new SortCompare(1000000);
		QuickSort<Double> ss = new QuickSort<>();
		System.out.println(sc.time(ss));
		//在测试中发现, 长度为0 和 1 的分别占到了 1/3的数组长度, 2仅仅是 1/10
		//可以通过 插入排序的介入, 进一步改进算法.
//		System.out.println("count0: " + ss.getCount0());
//		System.out.println("count1: " + ss.getCount1());
//		System.out.println("count2: " + ss.getCount2());
	}
	
	public static void testQuickSortImprove() {
//		SortCompare sc = new SortCompare(1000000);
//		QuickSortImprove<Double> ss = new QuickSortImprove<>();
//		System.out.println(sc.time(ss));
		
		//运行对比测试
		SortCompare sc = new SortCompare();
		QuickSortImprove<Double> ss = new QuickSortImprove<>();
		//QuickSortIgnoreSmallArray<Double> ss = new QuickSortIgnoreSmallArray<>();
		sc.time(ss, 10);
		
	}
	
	public static void testQuick3WaySort() {
		Random r = new Random(47);
		Integer[] array = new Integer[2];
		for (int i = 0, length = array.length; i < length; i++) {
			array[i] = r.nextInt(2);
		}
		
		SortCompare sc = new SortCompare(array);
		Quick3WaySort<Double> ss = new Quick3WaySort<>();
		//QuickSortImprove<Double> ss = new QuickSortImprove<>();
		//QuickSort<Double> ss = new QuickSort<>();
		//MergeSortImprove<Double> ss = new MergeSortImprove<>();
		//MergeSort<Double> ss = new MergeSort<>();
		//ShellSort<Double> ss = new ShellSort<Double>();
		System.out.println(sc.time(ss));
		
		//运行对比测试
//		SortCompare sc = new SortCompare();
//		Quick3WaySort<Double> ss = new Quick3WaySort<>();
//		sc.time(ss, 10);
		
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
