package zyx.algorithms4th.unitthree;

import java.util.Date;

public class BasicTest {

	private ST<String, Integer> st;
	
	private String[] key;
	
	private Integer[] value;
	
	@SuppressWarnings("unchecked")
	public BasicTest(String className, int length) {
		try {
			st = (ST<String, Integer>) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		key = new String[length];
		value = new Integer[length];
	}
	
	public void testPut() {
		initData();
		long start = new Date().getTime();
		put();
		long end = new Date().getTime();
		keys();
		System.out.println("putAll:" + (end - start));
	}
	
	public void testPut(int number) {
		long start = new Date().getTime();
		st.put("String" + number, number);
		long end = new Date().getTime();
		System.out.println("putOne:" + (end - start) + ", key:" + number);
	}
	
	private void keys() {
		for (String s: st.keys()) {
			System.out.print(s + ", ");
		}
	}
	
	public void testSearch(int index) {
		long start = new Date().getTime();
		st.get("String" + index);
		long end = new Date().getTime();
		System.out.println("searchKey:" + (end - start) + ", key:" + index);
	}
	
	private void put() {
		for (int i = 0, length = key.length; i < length; i++) {
			st.put(key[i], value[i]);
		}
	}
	
	private void initData() {
		String tempString;
		for (int i = 0, length = key.length; i < length; i++) {
			tempString = "String" + i;
			key[i] = tempString;
			value[i] = i;
		}
	}
}
