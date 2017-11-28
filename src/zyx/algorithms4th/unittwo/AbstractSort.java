package zyx.algorithms4th.unittwo;

public abstract class AbstractSort<T> {

	public abstract void sort(Comparable<T>[] a);
	
	public boolean less(Comparable<T> q, T p) {
		return q.compareTo(p) <= 0;
	}
	
	public void exchange(T[] a, int q, int p) {
		T temp = a[q];
		a[q] = a[p];
		a[p] = temp;
	}
	
	public void show(T[] a) {
		System.out.println("****************************");
		for (int i = 0, length = a.length; i < length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	@SuppressWarnings("unchecked")
	public boolean isSorted(Comparable<T>[] a) {
		for (int i = 0, length = a.length; i < length - 1; i++) {
			if (!less(a[i], (T)a[i+1])) {
				return false;
			}
		}
		return true;
	}
}
