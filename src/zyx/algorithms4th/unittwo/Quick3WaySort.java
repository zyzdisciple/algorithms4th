package zyx.algorithms4th.unittwo;

import java.util.Arrays;
import java.util.Collections;

public class Quick3WaySort<T> extends AbstractSort<T> {

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		Collections.shuffle(Arrays.asList(array));
		sort(0, array.length - 1);
	}
	
	@SuppressWarnings("unchecked")
	private void sort(int lo, int hi) {
		
		if (lo >= hi)
			return;
		
		int lf = lo, rg = hi, mid = lo + 1;
		int cmp;
		T v = (T) array[lo];
		
		while(mid <= rg) {
			
			cmp = array[mid].compareTo(v);
			if (cmp < 0) {
				exchange(lf++, mid++);
			} else if (cmp == 0) {
				mid++;
			} else {
				exchange(mid, rg--);
			}
		}
		
		sort(lo, lf - 1);
		sort(rg + 1, hi);
	}

}
