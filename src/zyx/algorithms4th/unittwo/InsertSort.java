package zyx.algorithms4th.unittwo;

/**
 * 对插入排序的核心, 是假定/使得 左侧已经有序,
 * 在测试中, 大约比选择排序要快 20% ~ 30%;
 * @author Administrator
 *
 * @param <T>
 */
public class InsertSort<T> extends AbstractSort<T> {

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		for (int i = 1, length = array.length; i < length; i++) {
			for (int j = i; j > 0 && less(j, j - 1); j--) {
				exchange(j, j - 1);
			}
		}
		
	}

}
