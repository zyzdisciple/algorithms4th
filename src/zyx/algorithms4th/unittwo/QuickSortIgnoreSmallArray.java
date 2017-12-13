package zyx.algorithms4th.unittwo;

import java.util.Arrays;
import java.util.Collections;
/**
 * 习题2.3.27
 * @author Administrator
 *
 * @param <T>
 */
public class QuickSortIgnoreSmallArray<T> extends AbstractSort<T>{

	@Override
	public void sort() {
		Collections.shuffle(Arrays.asList(array));
		sort(0, array.length - 1);
		InsertSort.sort(array, 0, array.length - 1);
	}
	
	public void sort(int lo, int hi) {
		
		if (hi <= lo + 4) {
			return;
		}
		
		int mid = partition(lo, hi);
		sort(lo, mid - 1);
		sort(mid + 1, hi);
	}
	
	private int partition(int lo, int hi) {
		
		int lf = lo, rg = hi + 1;
		
		int mid = getMid(lo);
		
		exchange(lo, mid);
		
		while (true) {
		
			while (less(++lf, lo) && lf < hi)
				;
			
			while (less(lo, --rg))
				;
			
			if (lf >= rg)
				break;
			
			exchange(lf, rg);
			
		}
		exchange(rg, lo);
		return rg;
	}
	
	@SuppressWarnings("unchecked")
	private int getMid(int lo) {
		
		//第一种实现方式
		if (array[lo].compareTo((T) array[lo + 1]) >=0) {
			if (array[lo].compareTo((T) array[lo + 2]) <= 0) {
				return lo;
			}
			if (array[lo + 1].compareTo((T) array[lo + 2]) >= 0) {
				return lo + 1;
			}
			return lo + 2;
		} else {
			if (array[lo].compareTo((T) array[lo + 2]) >= 0) {
				return lo;
			}
			if (array[lo + 1].compareTo((T) array[lo + 2]) >= 0) {
				return lo + 2;
			}
			return lo + 1;
		}
		
		/*
		//第二种实现方式
		InsertSort.sort(array, lo, lo + 4);
		return lo + 2;
		*/
	}
}
