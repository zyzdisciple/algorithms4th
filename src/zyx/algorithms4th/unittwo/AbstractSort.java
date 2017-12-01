package zyx.algorithms4th.unittwo;

public abstract class AbstractSort<T> {

	protected Comparable<T>[] array;
	
	public abstract void sort();
	
	@SuppressWarnings("unchecked")
	public boolean less(int q, int p) {
		return array[q].compareTo((T)array[p]) < 0;
	}
	
	public void exchange(int q, int p) {
		Comparable<T> temp = array[q];
		array[q] = array[p];
		array[p] = temp;
	}
	
	public void show() {
		System.out.println("****************************");
		for (int i = 0, length = array.length; i < length; i++) {
			System.out.print(array[i] + " ");
			if ((i + 1) % 4 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public boolean isSorted() {
		for (int i = 0, length = array.length; i < length - 1; i++) {
			if (!less(i, i + 1)) {
				return false;
			}
		}
		return true;
	}
	
	public void setArray(Comparable<T>[] array) {
		this.array = array;
	}
}
