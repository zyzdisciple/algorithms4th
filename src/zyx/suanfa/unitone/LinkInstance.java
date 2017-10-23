package zyx.suanfa.unitone;

/**
 * ������Ӧʵ��
 * @author Administrator
 *
 */
public class LinkInstance {

	//1.3.37
	public static void Josephus(int count, int number) {
		
		MyQueueWithLink<Integer> people = new MyQueueWithLink<>();
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
			System.out.println("��" + index + "�˱�ɱ��");
			count--;
		}
		
		System.out.println("����������" + (people.pop() + 1));
	}
	
	public static void main(String[] args) {
		Josephus(6, 5);
	}
}
