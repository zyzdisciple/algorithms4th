package zyx.algorithms4th.unittwo;

public class MergeSort<T> extends AbstractSort<T> {
	
	private Comparable<T>[] temp;

	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
		// TODO Auto-generated method stub
		temp = new Comparable[array.length];
		sort(0, array.length - 1);
	}
	
	private void sort(int lo, int hi) {
		if (lo >= hi)
			return;
		
		int mid = (lo + hi) / 2;
		sort(lo, mid);
		sort(mid + 1, hi);
		merge(lo, mid, hi);
		
	}
	
	@SuppressWarnings("unchecked")
	private void merge(int lo, int mid, int hi) {
		
		//这里不直接使用lo hi 做变量的原因之一是, 为了代码清晰, 为以后修改留下一定空间.
		//lf 和 rg 永远指向下一个需要被比较的元素;
		int lf = lo, rg = mid + 1;
		//复制数组, 这里之所以不在初始化时 copy数组, 在每完成一次合并之后, 数组的元素顺序事实上已经发生变化了.
		for (int i = lo; i <= hi; i++) {
			temp[i] = array[i];
		}
		
		//在遍历过程中, 向原数组的相应位置填充数值, 所以这里的 i 主要表示当前要填充的位置.
		//维护一个 lf 和 rg 分别是遍历两个被合并数组的指针
		for (int i = lo; i <= hi; i++) {
			
			if (lf > mid) {
				array[i] = temp[rg++];
			} else if (rg > hi) {
				array[i] = temp[lf++];
			} else if (less(temp[lf], temp[rg])) {
				array[i] = temp[lf++];
			} else {
				array[i] = temp[rg++];
			}
		}
		
	}

}
