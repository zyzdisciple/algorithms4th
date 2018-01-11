package zyx.algorithms4th.unitthree;

public class Test {

	public static void main(String[] args) {
		testBST();
	}
	
	public static void testBST() {
		BasicTest bt = new BasicTest("zyx.algorithms4th.unitthree.BinarySearchTree", 1000);
		bt.testPut();
		//bt.testSearch(188);
		ST<String, Integer> st = bt.getSt();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("rank: " + st.rank("String111", "String118"));
		System.out.println("isBinaryTree: " + st.isBinaryTree());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		st.printLevel();
//		System.out.println("max: " + st.max());
//		System.out.println("min: " + st.min());
//		System.out.println("isOrdered: " + st.isOrdered());
//		System.out.println("hasNoDuplicates: " + st.hasNoDuplicates());
//		for(String s :st.keys()) {
//			System.out.println(s);
//		}
//		st.delete("String180");
		//bt.testSearch(180);
//		System.out.println(st.contains("String180"));
		
//delete 和 这个联合测试, 说明实现了对应的 N 值变换		
//		System.out.println("find: " + st.select(92));
		
//找出当前需要查找元素的序列
//		for (int i = 1; i < 999; i++) {
//			//System.out.println("find: " + st.get("String" + i));
//			System.out.println("find: " + st.select(i) + " index: " + i);
//		}
		
	}
	
}
