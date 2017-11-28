package zyx.algorithms4th.unittwo;

public class SelectSort<T> extends AbstractSort<T> {

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparable<T>[] a) {
		// TODO Auto-generated method stub
		for (int i = 0, length = a.length; i < length; i++) {
			int min = i;
			for (int j = i; j < length; j++) {
				if (less(a[j], (T)a[i])) {
					min = j;
				}
			}
			exchange((T[])a, i, min);
		}
	}

}
