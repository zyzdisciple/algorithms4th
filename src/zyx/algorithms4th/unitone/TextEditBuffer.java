package zyx.algorithms4th.unitone;
/**
 * 将缓冲区分为前后两段, 用光标隔离开来;
 * @author zyzdisciple
 *
 */
public class TextEditBuffer {

	//扩写原有方法是不允许的, 因为 队列与 栈的 特点就是存取方式有所不同, 实现的并不是容器, 而是具有特点的数据结构;
	//前半段采用栈, LIFO 的特点, 可以从缓冲区的后半段截取;
	//形象说明的话, 前半段栈向右延伸, 头在右边, 后半段向左延伸, 头在左边
	//同时 frontBuffer 的 头表示光标所在的位置;
	private StackWithLink<Character> frontBuffer;
	//后半段同样采用 栈, 每次右移动的时候, 都是将最近插入的数据 pop取出来;
	private StackWithLink<Character> behindBuffer;
	
	public TextEditBuffer() {
		frontBuffer = new StackWithLink<Character> ();
		behindBuffer = new StackWithLink<Character> ();
	}
	
	public void insert(char c) {
		
		//插入新值永远在前边的栈, 也就是光标所在处插入数据即可.
		frontBuffer.push(c);
	}
	
	public Character delete() {
		
		if (frontBuffer.isEmpty()) {
			return null;
		}
		return frontBuffer.pop();
	}
	
	public void left(int count) {
		
		while(count > 0) {
			if (frontBuffer.isEmpty()) {
				break;
			}
			behindBuffer.push(frontBuffer.pop());
			count--;
		}
	}
	
	public void right(int count) {
		
		while(count > 0) {
			if (behindBuffer.isEmpty()) {
				break;
			}
			frontBuffer.push(behindBuffer.pop());
			count--;
		}
	}
	
	public int size() {
		
		return frontBuffer.size() + behindBuffer.size();
	}
	
}
