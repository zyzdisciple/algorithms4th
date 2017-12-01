package zyx.algorithms4th.unittwo;

public class Test {

	public static void main(String[] args) {
		//testSelectSort();
		//testInsertSort();
		testShellSort();
	}
	
	public static void testSelectSort() {
		SortCompare sc = new SortCompare(1000);
		SelectSort<Double> ss = new SelectSort<Double>();
		System.out.println(sc.time(ss));
	}
	
	public static void testInsertSort() {
		SortCompare sc = new SortCompare(10000);
		InsertSort<Double> ss = new InsertSort<Double>();
		System.out.println(sc.time(ss));
	}
	
	public static void testShellSort() {
		SortCompare sc = new SortCompare(10000);
		ShellSort<Double> ss = new ShellSort<Double>();
		System.out.println(sc.time(ss));
	}
}
