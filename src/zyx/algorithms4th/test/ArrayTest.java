package zyx.algorithms4th.test;

import java.util.Arrays;

public class ArrayTest {

	public static void main(String[] args) {
//		parallelSortTest();
//		sortTest();
		test2Array();
	}
	
	private static final void parallelSortTest() {
		int[] intArray = {1,3,2,4,9,6,3,7};
		Arrays.parallelSort(intArray);
		for (int i = 0, length = intArray.length; i < length; i++) {
			System.out.print(intArray[i] + " ");
		}
	}
	
	private static final void sortTest() {
		int[] intArray = {1,3,2,4,9,6,3,7};
		Arrays.sort(intArray);
		
		for (int i = 0, length = intArray.length, temp = 0; i < length / 2; i++) {
			temp = intArray[i];
			intArray[i] = intArray[length - i - 1];
			intArray[length - i - 1] = temp;
		}
		
		for (int i = 0, length = intArray.length; i < length; i++) {
			System.out.print(intArray[i] + " ");
		}
		
		int[] b = new int[13];
		System.arraycopy(intArray, 0, b, 0, 5);
		System.out.println();
		for (int i = 0, length = b.length; i < length; i++) {
			System.out.print(b[i] + " ");
		}
	}
	
	private static void test2Array() {
		int[][] intArray2 = { {1,2}, {3,4}, {5,6}, {7,8}};
		int[][] intArray3 = new int[5][3];
		
		System.arraycopy(intArray2, 0, intArray3, 0, 4);
		
		System.out.println(intArray3[3][2]);
		
		for (int[] array : intArray3) {
			for (int a : array) {
				System.out.println(a);
			}
		}
	}
	
}
