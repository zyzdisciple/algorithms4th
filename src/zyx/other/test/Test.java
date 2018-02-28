package zyx.other.test;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
	
	private static int i;
	
	public static void main(String[] args) {
		//diguiceshi();
		//yuhuofei();
		//yinyongchuandi();
		//sanliegongji();
		//arrayListTest();
		//linkedListTest();
		//integerTest();
		//comparableTest();
		//shuzuzengjiaTest();
		asciiTest();
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
			System.out.println("鍙互琚墽琛屽悧?");
		} else {
			System.out.println("鍝竴鍙ュ厛琚墽琛�?");
			return;
		}
	}
	
	public static void yinyongchuandi() {
		String s = new String("123");
		yinyongchuandi(s);
		System.out.println(s);
	}
	
	private static void yinyongchuandi(String s) {
		s = new String("456");
	}
	
	private static void sanliegongji() {
		
		System.out.println('A' + 1);
	}
	
	private static void arrayListTest() {
		ArrayList<String> list = new ArrayList<>(26);
		int count = 10, i = 0;
		while (count <= 20000) {
			i++;
			count = count + (count >> 1);
			System.out.println(count);
		}
	}
	
	private static void linkedListTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.remove(1);
		for (Integer i: list) {
			System.out.println(i);
		}
	}
	
	private static void integerTest() {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(111);
		Integer i22 = new Integer(111);
		Integer i3 = new Integer(1111);
		Integer i33 = new Integer(1111);
		System.out.println(i2 == i22);
		System.out.println(i3 == i22);
	}
	
	private static void comparableTest() {
		String a = "123";
		String b = "";
		String c = null;
		String d = null;
		System.out.println(b.compareTo(d));
	}
	
	private static void shuzuzengjiaTest() {
		int[] a = {1};
		a[0]++;
		System.out.println(a[0]);
	}
	
	private static void asciiTest() {
		System.out.println('z' + 1);
		System.out.println('0' + 1);
		System.out.println('9' + 1);
	}
}
