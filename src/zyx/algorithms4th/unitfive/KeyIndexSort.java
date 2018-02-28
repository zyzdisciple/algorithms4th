package zyx.algorithms4th.unitfive;

/**
 * 键索引计数法
 * 适用于小整数键的排序
 * 如:根据班级号, 组号, 省市等其他方式进行排序
 * @author Administrator
 *
 */
public class KeyIndexSort {

	public static void sort(User[] users) {
		
		int[] count = new int[11];
		User[] aux = new User[users.length];
		//频率统计
		for (int i = 0; i < users.length; i++) {
			count[users[i].getGroup() + 1]++;
		}
		
		//频率转索引, 这里的count[0] 从0开始
		for (int i = 0; i < 10; i++) {
			count[i + 1] += count[i];
		}
		
		//获取到对应的索引起点, 填充之后+1
		for (int i = 0; i < users.length; i++) {
			aux[count[users[i].getGroup()]++] = users[i];
		}
		
		//回写
		
		System.arraycopy(aux, 0, users, 0, users.length);
	}
}
