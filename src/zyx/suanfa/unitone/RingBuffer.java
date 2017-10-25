package zyx.suanfa.unitone;

/**
 * 难点: 因为如果是常规的数组存取的话, 和之前的实现方式相同, 则会出现每次进行(存取均有)都需要 copy 数组, 带来开销, 
 * 但数组的存取频率又会特别高, 所以即使能实现, 代价依旧太高
 * 需要自行判断数组是否为空 或已经满了;
 * @author zyzdisciple
 *
 * @param <T>
 */
public class RingBuffer<T> {

	private Object[] elementData;
	private static final int DEFAULT_SIZE = 16;
	//实时表示数组当前的存量,控制线程等待或不等待
	private volatile int size;
	//保存进入的下标,表示从数组当前的哪一个位置填充
	private volatile int in;
	//保存出的下标, 表示数组从当前哪个index 取出
	private volatile int out;
	
	//初始化
	public RingBuffer() {
		
		elementData = new Object[DEFAULT_SIZE];
	}
	
	//接受长度, 初始化
	public RingBuffer(int length) {
		
		if (length <= 0) {
			throw new RuntimeException("illegal argument: " + length);
		}
		elementData = new Object[length];
	}
	
	
	//向缓冲区填充数据, 返回值不为 boolean, 使得add仅仅是填充数据, 不做 isFull()的功能
	public void add(T item) {
		
		if (isFull()) {
			throw new RuntimeException("缓冲区已满");
		}
		elementData[in++] = item;
		ringIn();
		size++;
	}
	
	//取出数据
	@SuppressWarnings("unchecked")
	public T get() {
		
		if (isEmpty()) {
			throw new RuntimeException("缓冲区为空");
		}
		T item =(T) elementData[out];
		elementData[out++] = null;
		ringOut();
		size--;
		return item;
	}

	//2017年10月25日08:45:47
	//扩容/缩容   缓冲区
	public void resizeBuffer(int capacity) {
		int tempSize = size;
		if (capacity <= size) {
			throw new RuntimeException("容量设置过小");
		}
		Object[] newObj = new Object[capacity];
		int index = 0;
		while(!isEmpty()) {
			
			newObj[index++] = get();
		}
		
		elementData = newObj;
		size = tempSize;
		out = 0;
		in = size - 1;
	}
	
	//当前缓冲区容量
	public int size() {
		
		return size;
	}
	
	//缓冲区是否已满
	public boolean isFull() {
		
		return size >= elementData.length;
	}
	
	//缓冲区是否为空
	public boolean isEmpty() {
		
		return size <= 0;
	}
	
	//清空缓冲区
	public void clear() {
		//直接删除原数组的引用, 以清空数组;方便回收;
		Object[] tempArray = new Object[elementData.length];
		elementData = tempArray;
		in = out = size = 0;
	}
	
	private void ringIn() {
		//如果数组的 in 到达末尾, 此时in++为 length, 则指向数组的 0 位置
		if (in == elementData.length) {
			in = 0;
		}
	}
	
	private void ringOut() {
		//当out指向当前要取出数据的位置, 则当 指向末尾时, out++ 则为 length, 需要去指向数组的开头;
		if (out == elementData.length) {
			out = 0;
		}
	}
	
	public static void main(String[] args) {
		RingBuffer<String> rb = new RingBuffer<>();
		int index = 0;
		for(int i = 0; i < 16; i++) {
			if (!rb.isFull()) {
				rb.add("这是第" + index + "个数的第一次添加");
				index++;
			}
		}
		
		for (int i = 0; i < 12; i++) {
			System.out.println(rb.get());
		}
		
		for(int i = 0; i < 12; i++) {
			if (!rb.isFull()) {
				rb.add("这是第" + index + "个位置的第二次添加");
				index++;
			}
		}
		
		for(int i = 0; i < 7; i++) {
			if (!rb.isEmpty()) {
				System.out.println(rb.get());
			}
		}
		
		for(int i = 0; i < 12; i++) {
			if (!rb.isFull()) {
				rb.add("这是第" + index + "个位置的第三次添加");
				index++;
			}
		}
		
		rb.resizeBuffer(25);
		for (int i = 0; !rb.isEmpty(); i++) {
			System.out.println(rb.get());
		}
	}
}
