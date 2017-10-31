package zyx.algorithms4th.test;

import zyx.algorithms4th.unitone.QueueWithLink;
import zyx.algorithms4th.unitone.StackWithLink;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {

//		MyStackWithLink<String> stack = new MyStackWithLink<>();
		QueueWithLink<String> stack = new QueueWithLink<>();
		for (int i = 0; i < 10; i++) {
			stack.push(null);
			stack.push("abc");
		}
		
		
		String str = null;
		
		stack.remove(str);
		stack.remove("abc");
		System.out.println(stack.size());
		for (String s : stack) {
			System.out.println(s + "");
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
