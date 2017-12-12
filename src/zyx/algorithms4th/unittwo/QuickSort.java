package zyx.algorithms4th.unittwo;

import java.util.Arrays;
import java.util.Collections;

/**
 * 如果已经有序, 再打乱排序.需要判断
 * @author Administrator
 *
 * @param <T>
 */
public class QuickSort<T> extends AbstractSort<T> {
	
	/*习题2.3.7*/
	//private int count0, count1, count2;

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		Collections.shuffle(Arrays.asList(array));
		sort(0, array.length - 1);
	}
	
	public void sort(int lo, int hi) {
		
		/*习题2.3.7*/
//		int length = hi - lo + 1;
//		switch (length) {
//		case 0:
//			count0++;
//			break;
//		case 1:
//			count1++;
//			break;
//		case 2:
//			count2++;
//		default:
//			break;
//		}
		/*习题2.3.7*/
		
		if (lo >= hi)
			return;
		
		int segmentation = partition(lo, hi);
		sort(lo, segmentation - 1);
		sort(segmentation + 1, hi);
	}
	
	private int partition(int lo, int hi) {
		
		int lf = lo, rg = hi + 1;
		
		while (true) {
			//因为在这里交换的是 lf 和 rg 的当前位置, 所以两者必须表示比较完成后的值,因为同样的原因, rg 的起始值为 hi + 1;
			while (less(++lf, lo) && lf < hi)
				;
			
			while (less(lo, --rg) && rg > lo)
				;
			
			/**
			 * 在这里存在一个小问题
			 * e.g.: lf为6 rg为7, 本次条件满足 将 array[6] 和 array[7]交换, 此时:
			 * array[6] < array[lo], array[7] > array[lo]
			 * 在下一次循环中, 即使条件均不满足
			 * lf -> 7, rg -> 6
			 * 如果将 exchange(lf ,lo), 则此时的 array[lo] > array[7]的;
			 * 如果将exchange(rg, lo), 则此时 array[lo] < array[6]. 同时 array[7] > array[6]
			 * 所以只能交换 rg 和 lo;
			 *  */
			if (lf >= rg) {
				break;
			}
			exchange(lf, rg);
		}
		exchange(rg, lo);
		return rg;
	}

	/**
	 * 习题2.3.7
	 * @return
	 */
//	public int getCount0() {
//		return count0;
//	}
//
//	public int getCount1() {
//		return count1;
//	}
//
//	public int getCount2() {
//		return count2;
//	}
	
	

}
