package zyx.suanfa.unitone;

import java.util.Iterator;
/**
 * 由于栈本身的特点就是, 先进后出, 因此仅实现以下几个方法即可;
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
