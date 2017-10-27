package zyx.algorithms4th.unitone;

public class UnitOneExercisePointFour {
	
	 /**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(e_4("([)")); 
		 
	}
	 
	private static boolean e_4(String s) {
		StackWithLink<String> stack = new StackWithLink<> ();
		String[] strArray = s.split("");
		String strs = "{[()]}";
		for (String str : strArray) {
			
			int index = strs.indexOf(str);
			if (index > 2) {
				int matchIndex;
				while(!stack.isEmpty()) {
					matchIndex = strs.indexOf(stack.pop());
					if (matchIndex == -1) {
						continue;
					} else {
						return (matchIndex + index) == 5;
					}
				}
				
			} else {
				stack.push(str);
			}
			
		}
		return false;
	}
}
