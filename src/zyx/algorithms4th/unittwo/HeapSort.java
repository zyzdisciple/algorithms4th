package zyx.algorithms4th.unittwo;

/**
 * 堆排序
 * @author Administrator
 *
 */
public class HeapSort<T> extends AbstractSort<T> {

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		int length = array.length;
		for (int i = length / 2; i >= 1; i--) {
			sink(i, length);
		}
		
		while (length > 1) {
			exchange(1, length--);
			sink(1, length);
		}
	}
	
	private void sink(int k, int n) {
		
		int i;
		while (2 * k <= n) {
			i = 2 * k;
			if (i < n && less(i, i + 1)) {
				i++;
			}
			
			if (less(k, i)) {
				exchange(i, k);
				k = i;
			} else {
				break;
			}
		}
	}

	@Override
	public boolean less(int q, int p) {
		// TODO Auto-generated method stub
		return super.less(q - 1, p - 1);
	}

	@Override
	public void exchange(int q, int p) {
		// TODO Auto-generated method stub
		super.exchange(q - 1, p - 1);
	}
	
	

}
