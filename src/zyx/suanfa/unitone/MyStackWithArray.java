package zyx.suanfa.unitone;

import java.util.Iterator;

public class MyStackWithArray<T> implements Iterable<T>{
	
	private int size;
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public MyStackWithArray() {
		array =(T[]) new Object[4];
	}
	
	@SuppressWarnings("unchecked")
	public MyStackWithArray(int size) {
		array =(T[]) new Object[size];
	}
	
	public boolean push(T e) {
		if (isFull()) {
			resize((size + 1) * 2);
		}
		array[size++] = e;
		return true;
	}
	
	public T pop() {
		
		if (size <= 0) {
			throw new RuntimeException("error of zero");
		}
		if (size <= (array.length >> 2) && size >= 4 ) {
			resize(array.length >> 1 + 1);
		}
		T temp = array[--size];
		array[size] = null;
		return temp;
	}
	
	public int size() {
		return size;
	}
	
	private boolean isFull() {
		return size == array.length;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int length) {
		T[] temp = (T[]) new Object[length];
		for (int i = 0; i < size; i++) {
			temp[i] = array[i];
		}
		array = temp;
	}

	@Override
	public Iterator iterator() {
		return new MyStackIterator();
	}
	
	private class MyStackIterator implements Iterator<T> {

		private int n = size;
		
		@Override
		public boolean hasNext() {
			return n > 0;
		}

		@Override
		public T next() {
			
			return array[--n];
		}
		
	}

}
