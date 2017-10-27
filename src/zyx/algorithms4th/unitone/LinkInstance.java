package zyx.algorithms4th.unitone;

/**
 * 创建对应实例
 * @author Administrator
 *
 */
public class LinkInstance {

	//1.3.37
	public static void Josephus(int count, int number) {
		
		QueueWithLink<Integer> people = new QueueWithLink<>();
		if (count <= 0) {
			throw new RuntimeException("illegalArgument: count" + count);
		}
		
		if (number <= 0) {
			throw new RuntimeException("illegalArgument: number" + number);
		}
		
		for (int i = 0; i < count; i++) {
			people.push(i);
		}
		
		while( count != 1) {
			int killIndex = (number % count == 0 ? number : number % count) - 1;
			int index = people.remove(killIndex) + 1;
			System.out.println("第" + index + "人被杀死");
			count--;
		}
		
		System.out.println("活下来的是" + (people.pop() + 1));
	}
	
	public static void main(String[] args) {
		Josephus(6, 5);
	}
}
