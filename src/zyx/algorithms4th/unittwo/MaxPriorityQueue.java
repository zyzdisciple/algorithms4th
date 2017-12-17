package zyx.algorithms4th.unittwo;

public class MaxPriorityQueue<Key extends Comparable<Key>> {
	
	private Key[] array;
	private int size;
	private final static int DEFAULT_SIZE = 16;
	
	//API设计
	public MaxPriorityQueue() {
		
		new MaxPriorityQueue<Key>(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public MaxPriorityQueue(int maxSize) {
		
		array = (Key[]) new Comparable[maxSize + 1];
	}
	
	@SuppressWarnings("unchecked")
	public MaxPriorityQueue(Key[] a) {
		
		array = (Key[]) new Comparable[a.length + 1];
		int length = a.length;
		System.arraycopy(a, 0, array, 1, length);
		
		for (int i = length / 2; i >= 1; i--) {
			sink(i);
		}
		size = length;
	}
	
	public Key delMax() {
		
		Key key = array[1];
		exchange(1, size--);
		array[size + 1] = null;
		sink(1);
		
		return key;
	}
	
	public Key max() {
		
		return array[1];
	}
	
	public void insert(Key key) {
		
		array[++size] = key;
		swim(size);
	}
	
	public boolean isEmpty() {
		
		return size == 0;
	}
	
	public int size() {
		
		return size;
	}
	
	private void swim(int k) {
		
		while (k > 1 && less(k, k >> 1)) {
			exchange(k, k >> 1);
			k = k >> 1;
		}
	}
	
	private void sink(int k) {
		
		int i;
		while (2 * k <= size) {
			i = k << 1;
			if (i < size && less(i, i + 1)) {
				i++;
			}
			
			if (i < size && less(k, i)) {
				exchange(k, i);
				k = i;
			} else {
				break;
			}
			
		}
	}
	
	private boolean less (int i, int j) {
		return array[i].compareTo(array[j]) < 0;
	}
	
	private void exchange(int i, int j) {
		Key temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	//未完成
	private void resize(int length) {
	
		int s = array.length - 1;
		if (length >= (int) s * 0.75) {
			s = s * 2 + 1;
			
		} else if (length <= s / 4) {
			s = s >> 1;
		}
	}
}
