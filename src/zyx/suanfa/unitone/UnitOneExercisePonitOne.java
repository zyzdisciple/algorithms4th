package zyx.suanfa.unitone;

import java.util.Scanner;

public class UnitOneExercisePonitOne {

	
	private static void e1() {
		System.out.println(1 + 2 + "3");
	}
	
	private static void e9() {
		Integer a = 160;
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toUnsignedString(a, 16));
		String s2 = Integer.toBinaryString(a);
		Integer b = Integer.parseInt(s2, 2);
		System.out.println(b);
	}
	
	private static void e13() {
		int[][] a = {{1,2},{3,4}, {5,6}};
		for (int i = 0, width = a[0].length, length = a.length; i < width; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(a[j][i]);
			}
			System.out.println();
		}
	}
	
	private static int e14(int n) {
		
		if (n <= 0) {
			throw new RuntimeException("n只能为正数");
		} else  {
			int result = 0;
			
			for (; pow(2, result) <= n; result++)
				;
			return --result;
		}
	}
	/**
	 * a的b次幂
	 * @param a
	 * @param b
	 * @return
	 */
	private static double pow(int a, int b) {
		
		if (a == 0 || a == 1) {
			return a;
		} else if (a == -1) {
			return b % 2 == 0 ? 1 : -1;
		} else {
			if (b == 0) {
				return 1;
			} else {
				double result = 1;
				for (int i = 0; i < b; i++) {
					result *= a;
				}
				
				if (b < 0) {
					result = 1 / result;
				}
				
				return result;
			}
			
		}
	}
	/**
	 * 乘法
	 * @param a
	 * @param b
	 * @return
	 */
	private static int e18(int a, int b) {
		if (b == 0 )
			return 0;
		if (b % 2 == 0)
			return e18(a + a, b / 2);
		return e18(a + a, b / 2) + a;
	}
	
	/**
	 * n的阶乘
	 * @param n
	 * @return
	 */
	private static int e20_1(int n) {
		
		if (n < 0) {
			throw new RuntimeException("不能为负");
		}
		if (n < 2) {
			return n;
		}
		return n * e20_1(n - 1);
	}
	
	private static int binarySearch(int[] array, int aim) {
		int hi = array.length, lo = 0, mid;
		while(true) {
			mid = (hi + lo) >> 1;
		try {
			if (array[mid] == aim)
				return mid;
		} catch(Exception e) {
			return -1;
		}
			if (mid == array.length || mid == 0)
				return -1;
			
			if (array[mid] > aim) {
				hi = mid - 1;
			} else { 
				lo = mid + 1;
			}
		}
	}
	
	private static int e_22(int[] array, int aim) {
		return e_22_1(array, aim, 0, array.length, 0);
	}
	
	private static int e_22_1(int[] array, int aim, int lo, int hi, int deep) {
		
System.out.println(deep);

		if (lo > hi) {
			return -1;
		}
		
		int mid = (lo + hi) >> 1;
		if (array[mid] == aim) {
			return mid;
		}
		if (array[mid] > aim) {
			return e_22_1(array, aim, lo, hi - 1, ++deep);
		} else {
			return e_22_1(array, aim, lo + 1, hi, ++deep);
		}
	}
	
	private static int e_24(int a, int b) {
		
		if (b == 0) {
			return a;
		}
		
		return e_24(b , a % b);
	}
	
	private static int e_24_1(int a, int b) {
		
		if (a == 0) {
			return a;
		}
		
		return e_24(a % b , b);
	}
	/**
	 * 无法完成, 因为 无论怎样都会存在数据丢失, 无法保存的现象, 但对于中位数而言, 需要所有数据才能确定.
	 * 当结果与 数组长度相关时, 即无法实现.
	 */
	private static void e_34_2() {
		
		int[] array = {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE};
		Scanner sc = new Scanner(System.in);
		int number;
		array[1] = array[2] = sc.nextInt();
		boolean isOdd = true;
		
		while (true) {
			number = sc.nextInt();
			if (number == -1) {
				break;
			}
			isOdd = !isOdd;
			
			if(array[0] > number) {
			}
			if (array[0] < number && number <= array[1]) {
				array[0] = number;
			}
			if (array[1] < number && number <= array[2]) {
				array[0] = array[1];
				array[1] = number;
			}
			if (array[2] < number && number <= array[3]) {
				array[3] = number;
			}
			
		}
	}
	
	private static void shuffle() {
	}
	
	
	public static void main(String[] args) {
//		e1();
//		e9();
//		e13();
//		System.out.println(e14(1));
//		System.out.println(e18(3, -11));
		
//		System.out.println(LocalDateTime.now());
//		System.out.println(e20_1(30));
//		System.out.println(LocalDateTime.now());
		
//		int[] array = {1,2,3,4,5,6,7};
//		System.out.println(e_22(array, 6));
		
//		System.out.println(e_24_1(11245692, 12345678));
//		System.out.println(-14 % 4);
//		e_34_2();
		
		System.out.println(1 << 31);
		System.out.println(Integer.parseInt("1111111111111111111111111111111", 2));
		System.out.println(Integer.toBinaryString(-2147483647));
	}
	
}
