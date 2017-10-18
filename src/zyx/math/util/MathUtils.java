package zyx.math.util;

import java.util.HashSet;
import java.util.Set;

public class MathUtils {

	/**
	 * 计算a 的 b次方
	 */
	public static double pow(int a, int b) {

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
	 * 删除重复元素
	 */
	public static Integer[] deleteRepeatedElement(int[] array) {
		
		int length = array.length;
		Set<Integer> set = new HashSet<>(length);
		for (int i = 0; i < length; i++) {
			set.add(array[i]);
		}
		
		return set.toArray(new Integer[set.size()]);
	}
	
	/**
	 * 求取最大公约数
	 */
	public static int gcd(int a, int b) {
		
		if (b == 0) {
			return a;
		}
		
		return gcd(b , a % b);
	}
	
	/**
	 * 回环变位
	 */
	public static boolean circularRotation(String s, String t) {
		
		return (t + t).indexOf(s) != -1 && s.length() == t.length();
 	}
	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,5,5,5,6,7,8,9,9,9,1};
		Integer[] a = deleteRepeatedElement(array);
		System.out.println(a.length);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
