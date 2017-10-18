package zyx.test;

import zyx.suanfa.unitone.MyQueueWithLink;
import zyx.suanfa.unitone.MyStackWithLink;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {

//		MyStackWithLink<String> stack = new MyStackWithLink<>();
		MyQueueWithLink<String> stack = new MyQueueWithLink<>();
		for (int i = 0; i < 10; i++) {
			stack.push("string:" + i);
		}
		
		for (String s : stack) {
			System.out.println(s);
		}
		
		System.out.println("**********************************");
		
		System.out.println(stack.remove(0));
		
		System.out.println("**********************************");
		
		stack.removeAfter(5);
		
		System.out.println(stack.size());
		for (int i = 0, length = stack.size(); i < length; i++) {
			System.out.println(stack.pop());
		}
//		LinkedList
//		System.out.println(stack.pop());
//		System.out.println(stack.isEmpty());
	}
}
