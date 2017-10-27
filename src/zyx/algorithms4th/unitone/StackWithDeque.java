package zyx.algorithms4th.unitone;

public class StackWithDeque<T> {

	private final Deque<Object> deque;
	//通过 size 控制左右两个栈的界限;
	private int fSize;
	private int sSize;
	
	public StackWithDeque() {
		deque = new Deque<> ();
	}
	
	//第一个栈的 进栈操作;
	public void fPush(T item) {
		deque.pushLeft(item);
		fSize++;
	}
	
	public void sPush(T item) {
		deque.pushRight(item);
		sSize++;
	}
	
	@SuppressWarnings("unchecked")
	public T fPop() {
		if (fSize == 0) {
			throw new RuntimeException("栈为空");
		}
		fSize--;
		return (T) deque.popLeft();
	}
	
	@SuppressWarnings("unchecked")
	public T sPop() {
		if (sSize == 0) {
			throw new RuntimeException("栈为空");
		}
		sSize--;
		return (T) deque.popRight();
	}
	
	public boolean fIsEmpty() {
		return fSize == 0;
	}
	
	public boolean sIsEmpty() {
		return sSize == 0;
	}
	
	public static void main(String[] args) {
		StackWithDeque<String> swd = new StackWithDeque<>();
		for (int i = 0; i < 10; i++) {
			swd.fPush("" + i);
			swd.fPush("" + i + 10);
			swd.fPush("" + i + 10);
		}
		
		while(!swd.fIsEmpty()) {
			System.out.println(swd.fPop());
		}
	}
}
