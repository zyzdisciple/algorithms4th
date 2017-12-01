package zyx.algorithms4th.unittwo;

/**
 * 希尔排序, 假定/使得  H有序, 进一步排序, 在 10000 数量级下, 比插入排序快 10倍以上!
 * @author Administrator
 *
 * @param <T>
 */
public class ShellSort<T> extends AbstractSort<T>{
	
	private int getH() {
		int h = 1;
		int length = array.length;
		while (h < length / 3) {
			
			if (h < array.length / 3) {
				h = h * 3 + 1;
				continue;
			}
			break;
		}
		return h;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		int h = getH();
		while (h >= 1) {
			for (int i = h, length = array.length; i < length; i++) {
				for (int j = i; j >= h && less(j, j - h); j -= h) {
						exchange(j, j - h);
				}
			}
			h /= 3;
		}
	}

}
