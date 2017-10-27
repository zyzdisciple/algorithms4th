package zyx.algorithms4th.unitone;

/**
 * 判断栈的可生成性
 * @author zyzdisciple
 *
 */
public class GenerableStack {
	
	private StackWithLink<Character> stack;
	
	public GenerableStack(String str) {
		stack = new StackWithLink<>();
		char[] array = str.toCharArray();
		//必须倒序放入才能顺序读出;
		for (int i = array.length - 1; i >= 0 ; i--) {
			stack.push(array[i]);
		}
	}
	
	//1.3.45 可生成性判断
	public boolean isGenerable() {
		int in = 0,out = 0;
		Character oC = '-';
		
		for (Character c : stack) {
			
			if (oC.equals(c)) {
				out++;
			} else {
				in++;
			}
			if (out > in) {
				return false;
			}
		}
		return true;
	}
	
	//1.3.45 验证给定排列
	public static boolean verify(String srcStr, String aimStr) {
		GenerableStack srcStack = new GenerableStack(srcStr);
		if (!srcStack.isGenerable()) {
			throw new RuntimeException("数据格式错误,无法生成栈");
		}
		
		StringBuilder tempStr = new StringBuilder();
		Character oC = '-';
		for(Character c : srcStack.stack) {
			if (!oC.equals(c)) {
				tempStr.append(c.toString());
			}
		}
		//对待这里的字符串, 应该是顺序放入, 表示出栈次序; 和栈的顺序一致;做过倒序处理,因此需要再次反序;
		return tempStr.reverse().toString().equals(aimStr);
	}
	
	public static void main(String[] args) {
		String str = "0-1-2-0-0-8-9--99-4";
		GenerableStack stack = new GenerableStack(str);
		System.out.println(stack.isGenerable());
		System.out.println(GenerableStack.verify("6-5-4-9-8-7-3-2-1-0", "0123456789"));
	}
}
