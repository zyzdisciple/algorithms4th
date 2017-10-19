package zyx.suanfa.unitone;

import java.util.Iterator;
/**
 * 鐢变簬鏍堟湰韬殑鐗圭偣灏辨槸, 鍏堣繘鍚庡嚭, 鍥犳浠呭疄鐜颁互涓嬪嚑涓柟娉曞嵆鍙�;
 * @author Administrator
 *
 * @param <T>
 */
public class MyStackWithLink<T> implements Iterable<T> {

	private Node top;
	private int size;
	
	public void push(T item) {
		
		Node oldNode = top;
		top = new Node();
		top.item = item;
		top.next = oldNode;
		size++;
	}
	
	public T pop() {
		Node temp = top;
		top = top.next;
		size--;
		return temp.item;
	}
	
	public T peek() {
		if (isEmpty()) {
			throw new RuntimeException("empty");
		} else {
			return top.item;
		}
	}
	
	public boolean contain(MyStackWithLink<T> stack, T item) {
		for (T t : stack) {
			if (item.equals(t)) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		
		return top == null;
	}
	
	//1.3.30
	//核心, 始终保持虚拟节点指向首节点, 将要反转的节点调整至首节点的位置即可.
	public Node reserve1(Node node) {
		
		if (node == null) {
			return node;
		}
		//定义虚拟节点始终指向首节点
		Node dummy = new Node();
		dummy.next = node;
		Node pre = dummy.next;
		Node pCur = pre.next;
		
		while (pCur != null) {
			//令原链表的首节点指向新表的剩余节点
			pre.next = pCur.next;
			//令将反转的节点指向现在的首节点
			pCur.next = dummy.next;
			//令虚拟节点指向新链表的首节点
			dummy.next = pCur;
			//反转节点下移一位
			pCur = pre.next;
		}
		
		return dummy.next;
	}
	
	public Iterator<T> iterator() {
		return new MyStackIterator();
	}
	
	private class Node {
		T item;
		Node next;
	}
	
	private class MyStackIterator implements Iterator<T> {

		Node cursor = top;
		
		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public T next() {
			T item = cursor.item;
			cursor = cursor.next;
			return item;
		}
	}
}
