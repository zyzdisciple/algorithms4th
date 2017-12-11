package zyx.algorithms4th.unittwo;

public class MergeSortImprove<T> extends AbstractSort<T> {
	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
		// TODO Auto-generated method stub
		Comparable<T>[] temp = new Comparable[array.length];
		sort(0, array.length - 1, array, temp);
	}

	private void sort(int lo, int hi, Comparable<T>[] src, Comparable<T>[] temp) {

		if (hi - lo <= 1) {
			sort(lo, hi, src);
			return;
		}

		int mid = (lo + hi) / 2;
		sort(lo, mid, temp, src);
		sort(mid + 1, hi, temp, src);
		
		merge(lo, mid, hi, temp, src);

	}

	private void merge(int lo, int mid, int hi, Comparable<T>[] src, Comparable<T>[] dest) {

		// 这里不直接使用lo hi 做变量的原因之一是, 为了代码清晰, 为以后修改留下一定空间.
		// lf 和 rg 永远指向下一个需要被比较的元素;
		int lf = lo, rg = mid + 1;

		// 在遍历过程中, 向目标数组的相应位置填充数值, 所以这里的 i 主要表示当前要填充的位置.
		// 维护一个 lf 和 rg 分别是遍历两个被合并数组的指针
		for (int i = lo; i <= hi; i++) {

			if (lf > mid) {
				dest[i] = src[rg++];
			} else if (rg > hi) {
				dest[i] = src[lf++];
			} else if (less(src[lf], src[rg])) {
				dest[i] = src[lf++];
			} else {
				dest[i] = src[rg++];
			}
		}

	}

	private void sort(int lo, int hi, Comparable<T>[] src) {
		
		if (src == array) {
			if (less(lo, hi))
				return;
			else
				exchange(lo, hi);
		} else {
			if (less(lo, hi)) {
				src[lo] = array[lo];
				src[hi] = array[hi];
			} else {
				src[lo] = array[hi];
				src[hi] = array[lo];
			}
		}
	}

}
