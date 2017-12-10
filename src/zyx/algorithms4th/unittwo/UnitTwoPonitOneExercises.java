package zyx.algorithms4th.unittwo;

/**
 * 滞后
 * @author zyzdisciple
 *
 */
public class UnitTwoPonitOneExercises {

	/**
	 * 如果允许开辟新的数组, 对于一副扑克牌进行排序, 是没有意义的, 因为已经知道最终结果, 最快的速度莫过于
	 * 创建新的数组.又或者向元数组中重新填充元素, 得到一副已排序的扑克牌的最快方式, 是拿出一副新的牌组.
	 * 
	 * 但如果就现实来说: 如何将其按花色排序, 则简单很多, 记录每种花色已排序的数量, 初始值均为0
	 * A B C D 分别对应四种花色, 从上向下依次翻牌, 遇到对应花色, 分别将其插入
	 * A + 1; A + B + 1; A + B + C +1; 和位置不变 每归位一张牌, 对应数值 +1 即可.
	 * 
	 * @author zyzdisciple
	 *
	 * @param <T>
	 */
	private class Sort2113<T> extends AbstractSort<T> {

		@Override
		public void sort() {
		}
		
	}
	
	/**
	 * 出列排序的限制就在于, 仅仅能把控最上面的一张牌, 是怎样的:
	 * 因此 循环比较, 比较最上面两张牌, 大的留在最上, 小的放在牌底, 在第一轮的比较中就能够得到最大的牌;
	 * 将最大的牌放在最下面, 进行第二轮排序, 假设牌总数为 N, 在进行 N - 2 次比较后, 会得出当前数组第二大的牌;
	 * 当前牌堆内, 最上面为 第二大的牌, 第二张为最大的牌;
	 * 置于牌堆底部, 以此类推, 分别进行 N -3 , N - 4...比较, 直到 仅需进行 一次比较时, 次序即可排定
	 * 
	 * @author zyzdisciple
	 *
	 * @param <T>
	 */
	private class Sort2114<T> extends AbstractSort<T> {

		@Override
		public void sort() {
		}
		
	}
	
	public class Sort2124<T> extends AbstractSort<T> {

		@Override
		public void sort() {
			int min = 0;
			int length = array.length;
			for (int i = 0; i < length; i++) {
				if (less(i, min)) {
					min = i;
				}
			}
			exchange(0, min);
			
			if(length < 3)
				return;
			
			for (int i = 2; i < length; i++) {
				for (int j = i; less(j, j - 1); j--) {
					exchange(j, j - 1);
				}
			}
		}
		
	}
	
	/**
	 * 在测试中发现, 这种方法与原版的方法时间上相同, 因为事实上在每一次的比较成功, 效率上仅有微小的差别
	 * @author Administrator
	 *
	 * @param <T>
	 */
	public class Sort2125<T> extends AbstractSort<T> {

		@Override
		public void sort() {
			Comparable<T> temp;
			int j;
			// TODO Auto-generated method stub
			for (int i = 1, length = array.length; i < length; i++) {
				temp = array[i];
				j = i - 1;
				for (; j >= 0 && less(temp, array[j]); j--) {
					array[j + 1] = array[j];
				}
				array[j + 1] = temp;
			}
		}
		
	}
}
