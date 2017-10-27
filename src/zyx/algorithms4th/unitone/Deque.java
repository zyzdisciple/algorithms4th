package zyx.algorithms4th.unitone;

import java.util.Iterator;
import java.util.Optional;

/**
 * 双向队列,在实现这个双向队列之前一开始考虑的是通过  MyQueueWithLink 的方式直接 copy,
 * 后决定采取一种新的数据结构; 添加一个虚拟节点;
 *用一个队列实现两个 栈看似简单, 实则是基于 双向队列这个有力的提示下才完成的;
 * @author zyzdisciple
 *
 */
public class Deque<T> implements Iterable<T> {

	private Node top;
	private Node tail;
	private int size;
	
	public void pushLeft(T item) {
		Node oldNode = top;
		top = new Node();
		top.item = item;
		top.next = oldNode;
		size++;
		
		if (size == 1) {
			tail = top;
		} else {
			oldNode.previous = top;
		}
	}
	
	public void pushRight(T item) {
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
	
	public T popLeft() {
		if(size == 0) {
			throw new RuntimeException("队列为空");
		}
		Node temp = top;
		top = top.next;
		Optional.ofNullable(top).ifPresent((t) -> t.previous = null);
		size--;
		return temp.item;
	}
	
	public T popRight() {
		if(size == 0) {
			throw new RuntimeException("队列为空");
		}
		Node temp = tail;
		tail = tail.previous;
		Optional.ofNullable(tail).ifPresent((t) -> t.next = null);
		size--;
		return temp.item;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new DequeIterator();
	}

	private class Node {
		
		private T item;
		private Node previous;
		private Node next;
	}
	
	private class DequeIterator implements Iterator<T> {

		private Node next;
		
		DequeIterator() {
			// TODO Auto-generated constructor stub
			next = top;
		}

		@Override
		public boolean hasNext() {
			
			return next != null;
		}

		
		@Override
		public T next() {
			
			T temp = next.item;
			next = next.next;
			
			return temp;
		}
		
	}
	
	public static void main(String[] args) {
	}
	
}
