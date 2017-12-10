package zyx.algorithms4th.unittwo;
/**
 * 选择排序
 * @author zyzdisciple 2017年12月8日17:26:36
 *
 * @param <T>
 */
public class SelectSort<T> extends AbstractSort<T> {
	
	@Override
	public void sort() {
		// TODO Auto-generated method stub
		for (int i = 0, length = array.length; i < length; i++) {
			int min = i;
			for (int j = i; j < length; j++) {
				if (less(j, min)) {
					min = j;
				}
			}
			exchange(i, min);
		}
	}

}
