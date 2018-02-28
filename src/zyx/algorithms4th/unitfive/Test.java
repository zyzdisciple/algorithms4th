package zyx.algorithms4th.unitfive;

import java.util.Random;
import java.util.UUID;

import junit.framework.TestCase;

public class Test extends TestCase {

	public void testSort() {
		
		User[] users = new User[10000];
		User user;
		Random random = new Random(47);
		for (int i = 0; i < 10000; i++) {
			user = new User("User" + i, random.nextInt(9));
			users[i] = user;
		}
		long start = System.currentTimeMillis();
		KeyIndexSort.sort(users);
		System.out.println(System.currentTimeMillis() - start);
		for (int i = 0; i < 10000; i++) {
			System.out.println(users[i]);
		}
	}
	
	public void testSort2() {
		User[] users = new User[1000];
		User user;
		String str;
		for (int i = 0; i < 1000; i++) {
			str = UUID.randomUUID().toString().substring(0, 5);
			user = new User("User" + i, str);
			users[i] = user;
		}
		long start = System.currentTimeMillis();
		LSD.sort(users, 5);
		System.out.println(System.currentTimeMillis() - start);
		for (int i = 0; i < 1000; i++) {
			System.out.println(users[i]);
		}
	}
}
