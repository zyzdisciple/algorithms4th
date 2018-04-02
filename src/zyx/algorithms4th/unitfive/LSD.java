package zyx.algorithms4th.unitfive;

public class LSD {

	public static void sort(User[] users, int maxLength) {
		
		User[] aux = new User[users.length];
		for (int i = maxLength - 1; i >= 0; i--) {
			
			int[] count = new int[125];
			/*对待如身份证号 固定位15 18位的这两种类型,可以设置超出界限的数值. 在15位-18位的统计时, 跳过所有为15位的,
			不再次进行统计*/
			String tempStr;
			for (int j = 0; j < users.length; j++) {
				//System.out.println(users[j] + ",j:" + j + ",i:" + i);
				if ((tempStr = users[j].getsGroup()).length() - 1 < i) {
					count[1]++;
				} else {
					count[tempStr.charAt(i) + 2]++;
				}
			}
			
			for (int j = 0; j < 124; j++) {
				count[j + 1] += count[j];
			}
			
			for (int j = 0; j < users.length; j++) {
				if ((tempStr = users[j].getsGroup()).length() - 1 < i) {
					aux[count[0]++] = users[j];
				} else {
					aux[count[tempStr.charAt(i) + 1]++] = users[j];
				}
			}
			
			System.arraycopy(aux, 0, users, 0, users.length);
		}
	}
}
