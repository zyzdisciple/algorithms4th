package zyx.algorithms4th.unitone;

/**
 * 难点分析. 栈的特点是先入后出, 队列 FIFO, 有限次操作的完成;
 * 同时仅允许使用 栈的 三个方法, 其他均不可使用, 不开放;
 * 仍需完善, 目前无解!
 * 唯有使用6个栈可以实现;
 * 参考链接:
 * http://www.cnblogs.com/ikesnowy/p/7157813.html
 * @author zyzdisciple
 * 
 * @param <T>
 */
public class ThreeStackToAQueue {

	//内部栈
	private MyStack stack1;
	//中转栈
	private MyStack stack2;
	//永远指向一个空地址, 
	private MyStack stack3;
	//记录当前栈容量为多少
	private int size;
	//记录当前出队数量
	private int count;
	
	//进行栈之间的嵌套
	public ThreeStackToAQueue () {
		//栈的初始化
		stack1 = new MyStack();
		stack2 = new MyStack();
		stack3 = new MyStack();
		
		stack1.push(stack2);
	}
	
	//依然没有实现
	public void push(Object item) {
		
		if(size % 2 == 0) {
			stack2.push(stack3);
			stack2.push(item);
			size++;
		} else {
			stack3.push(stack2);
			stack3.push(item);
			size++;
		}
		
	}
	
	
	public Object pop() {
		Object item;
		if(count % 2 == 0) {
			stack2  = (MyStack) stack1.pop();
			item = stack2.pop();
			stack3 = (MyStack) stack2.pop();
			stack1.push(stack3);
			count++;
			size--;
		} else {
			stack3 = (MyStack) stack1.pop();
			item = stack3.pop();
			stack2 = (MyStack) stack3.pop();
			stack1.push(stack2);
			count++;
			size--;
		}
		
		return item;
	}
	
	//由于是无限曾嵌套, 虽然从内到外围 1-9 还是达不到目的;
	/*public void push(Object item) {

		//将innerstack取出 ,暂存在changeStack中,此时 storageStack 内部无元素;
		if (size % 2 != 1) {
			//此时在 storage中只有一个元素, 为 inner
			
			stack2.push(stack3.pop());
			//将 item 存入 storageStack 作为内部元素
			stack3.push(item);
			//将 inner栈取出 压在 item元素上方;
			stack3.push(stack2.pop());
			size++;
			//将 storageStack放入 innerStack, 保证在最外层的 stack 中只有一个元素;
			stack1.push(stack3);
		} else {
			//表示在  inner 中只有一个元素 为 storage
			stack2.push(stack1.pop());
			stack1.push(item);
			stack1.push(stack3);
			size++;
			stack3.push(stack1);
		}
	}*/
	
	/*public Object pop() {
		System.out.println("**************************");
		System.out.println(size);
		System.out.println("**************************");		
		if (size == 0) {
			throw new RuntimeException("栈为空");
		}
		Object item;
		if (size % 2 == 0) {
			//首先弹出 innerStack
			stack1 = (MyStack) stack3.pop();
			//innerStack中存有两个元素 , 分别为 storageStack 和 item;
			stack3 = (MyStack) stack1.pop();
			item = stack1.pop();
			size--;
			//保持最外层的栈中只有一个元素;
			stack1.push(stack3);
		} else {
			stack3 = (MyStack) stack1.pop();
			stack1 = (MyStack) stack3.pop();
			item = stack1.pop();
			size--;
			stack3.push(stack1);
		}
		
		return item;
	}*/
	
	public boolean isEmpty() {
		
		return size == 0;
	}
	
	private class MyStack {
		
		int size;
		Node top;
		
		void push(Object item) {
			Node oldNode = top;
			top = new Node();
			top.item = item;
			top.next = oldNode;
			size++;
		}
		
		Object pop() {
			if (size == 0) {
				throw new RuntimeException("栈为空");
			}
			Object item = top.item;
			top = top.next;
			size--;
			return item;
		}
		
		class Node{
			Object item;
			Node next;
		}
	}

	
	public static void main(String[] args) {
		ThreeStackToAQueue ttq = new ThreeStackToAQueue();
		for (int i = 0; i < 10; i++) {
			ttq.push("这是第" + i + "元素");
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(ttq.pop());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
}
