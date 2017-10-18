package zyx.suanfa.unitone;

import java.util.Iterator;

public class MyQueueWithLink<T> implements Iterable<T> {

	private int size;
	private Node top;
	private Node tail;
	
	public void push(T item) {
		
		Node oldNode = tail;
		tail = new Node();
		tail.item = item;
		tail.previous = oldNode;
		size++;
		
		if (size == 1) {
			top = tail;
		} else {
			oldNode.next = tail;
		}
	}
	
	public T pop() {
		
		final Node node = top;
		
		return node == null ? null : unLink(node);
	}
	
	public T remove(int index) {
		
		if (index >= size) {
			throw new RuntimeException("out of bound");
		}
		Node mid = node(index);
		
		return unLink(mid);
	}
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	public int size() {
		return size;
	}
	
	public T removeAfter(int index) {
		
		final Node node = node(index);
		node.next = null;
		if (node != tail) {
			tail = null;
		}
		size = index + 1;
		
		return node.item;
	}
	
	public static <T> MyQueueWithLink<T> insertAfter(MyQueueWithLink<T> first, MyQueueWithLink<T> second) {
		if (first == null && second == null) {
			return null;
		} else if (first == null || second == null) {
			return first == null ? second : first;
		} else {
			first.tail.next = second.top;
			second.top.previous = first.tail;
			first.tail = second.tail;
			first.size += second.size;
			second.top = null;
			return first;
		}
	}
	
	public static <T> int remove(MyQueueWithLink<T> stack, T t) {
		
		int index = 0;
		int length = stack.size;
		return 0;
	}
	
	private T unLink(Node node) {
		
		final Node pre = node.previous;
		final Node next = node.next;
		final T item = node.item;
		
		if (size == 1) {
			top = tail = null;
		} else if (pre == null) {
			top = next;
			top.previous = null;
		} else if (next == null) {
			tail = pre;
			tail.next = null;
		} else {
			next.previous = pre;
			pre.next = next;
		}
		
		size--;
		
		return item;
		
	}
	
	/**
	 * 2017.10.15
	 * @param index
	 * @return
	 */
	
	private Node node(int index) {
		
		if (index >= size || index < 0) {
			throw new RuntimeException("超出链表长度");
		}
		
		Node node;
		if (index < (size >> 1)) {
			node = top;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		} else {
			node = tail;
			for (int i = size - 1; i > index; i--) {
				node = node.previous;
			}
		}
		return node;
	}
	
	private class Node {
		T item;
		Node next;
		Node previous;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<T> {

		Node cursor = top;
		Node lastReturned;
		
		@Override
		public boolean hasNext() {
			
			return cursor != null; 
		}

		@Override
		public T next() {
			
			T temp = cursor.item;
			cursor = cursor.next;
			
			return temp;
		}
		
		@Override
		public void remove() {
			
		}
		
	}
}
