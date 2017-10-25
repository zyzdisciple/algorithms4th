package zyx.algorithms4th.unitone;

public class UnitOneExercisePointTwo {

	private static boolean e_6(String s, String t) {
		
		if (s.length() != t.length()) {
			return false;
		}
		
		for (int i = 0, length = s.length(); i < length; i++) {
			int index;
			boolean flag = false;
			for (int j = 0; j < length; j++) {
				 index = (i + j) % length;
				 
				if (s.charAt(index) != t.charAt(j)) {
					flag = false;
					break;
				}
				flag = true;
			}
			
			if (flag)
				return true;
		}
		return false;
	}
	
	private static boolean e_6_1(String s, String t) {
		
		return (t + t).indexOf(s) != -1 && s.length() == t.length();
 	}
	
	private static String e_7(String s) {
		int n = s.length();
		if (n <= 1)
			return s;
		String a = s.substring(0, n / 2);
		String b = s.substring(n / 2 , n);
		return e_7(a) + e_7(b);
	}
	
	public static void main(String[] args) {
	
//		System.out.println(e_6_1("aabbjkjkjkjksssd", "sssdaabbjkjkjkjk"));
//		System.out.println("sssddd".indexOf("ad"));
//		System.out.println(e_7("aabb56"));
	}
	
	class Rational {
		
		private final long numerator;
		private final long denominator;
		
		public Rational() {
			numerator = 1L;
			denominator = 1L;
		}
		
		public Rational(long n, long d) {
			this.denominator = d;
			this.numerator = n;
		}
		
		/**
		 * 约分
		 */
		private Rational fractionReduction(Rational r) {
			long gcd = gcd(numerator, denominator);
			return new Rational(r.numerator / gcd, r.denominator / gcd);
			
		}
		
		/**
		 * 最大公约数
		 */
		private long gcd(long a, long b) {
			
			if (b == 0) {
				return a;
			}
			
			return gcd(b , a % b);
		}

		/**
		 * 最小公倍数
		 */
		
	}
}
