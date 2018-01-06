package zyx.other.test;

public class Test {
	
	private static int i;
	
	public static void main(String[] args) {
		//diguiceshi();
		yuhuofei();
	}
	
	public static void yuhuofei() {
		System.out.println("" + (1==2 || 1 == 1 && 2 == 3));//false
		System.out.println("" + (1==1 || 1 == 1 && 2 == 3));//true
		System.out.println("" + (1==1 || 1 == 2 && 2 == 2));//true
	}
	
	public static void diguiceshi() {
		if (i == 0) {
			i++;
			diguiceshi();
			System.out.println("可以被执行吗?");
		} else {
			System.out.println("哪一句先被执行?");
			return;
		}
	}
}
