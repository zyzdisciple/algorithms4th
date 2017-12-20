package zyx.algorithms4th.test;

import java.util.Comparator;

public class User{

	private int age;
	
	private String name;
	
	public static final UserNameComparator USER_NAME_COMPARATOR = new UserNameComparator();
	
	public static final AgeComparator AGE_COMPARATOR = new AgeComparator();
	
	private static class UserNameComparator implements Comparator<User> {

		@Override
		public int compare(User o1, User o2) {
			// TODO Auto-generated method stub
			return o1.name.compareTo(o2.name);
		}
	}
	
	private static class AgeComparator implements Comparator<User> {

		@Override
		public int compare(User o1, User o2) {
			// TODO Auto-generated method stub
			return o1.age - o2.age;
		}
		
	}
}
