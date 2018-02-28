package zyx.algorithms4th.unitfive;

public class Alphabet {

	private char[] alphabet;
	private int size;
	
	public Alphabet(String s) {
		
		size = s.length();
		alphabet = new char[size];
		for (int i = 0; i < size; i++) {
			alphabet[i] = s.charAt(i);
		}
	}
	
	public char toChar(int index) {
		
		return index > size ? null : alphabet[index];
	}
	
	public int toIndex(char c ) {
		
		for (int i = 0; i < size; i++) {
			if (c == alphabet[i]) {
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean contains(char c) {
		
		return toIndex(c) < 0 ? false : true;
	}
	
	public int R() {
		
		return size;
	}
	
	public int[] toIndices(String s) {
		
		int[] a = new int[s.length()];
		for (int i = 0, length = s.length(); i < length; i++) {
			a[i] = toIndex(s.charAt(i));
		}
		
		return a;
	}
	
	public String toChars(int[] a) {
		
		StringBuilder s = new StringBuilder();
		for (int i = 0, length = a.length; i < length; i++) {
			s.append(toChar(a[i]));
		}
		
		return s.toString();
	}
}
