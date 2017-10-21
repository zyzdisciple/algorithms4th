package zyx.suanfa.unitone;

public class RandomBag<T> {

	//在java 中由于向上转型和向下转型的存在,同时两者之间转换存在一定的问题,java不支持泛型数组, 既然不
	//支持泛型数组, 不如就不定义泛型数组.仅在取出元素的时候, 转换为对应的类型即可;在方法的参数传递过程中,已经
	//可以对类型进行限定;
	//private T[] elementData;
	private Object[] elementData;
	
	private int size;
	
	private static final int DEFAULT_CAPACITY = 16;
	
	//参考ArrayList, 为了使得所有的空数组共同指向同一个地址, 则定义常量;
	private static final Object[] EMPTY_ELEMENTDATE = {};
	
	//根据ArrayList解释, 某些JVM会在 array前加上头,会导致outOfMemory
	private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
	
	//ArrayList
	
	public RandomBag() {
		elementData = new Object[DEFAULT_CAPACITY];
	}
	
	public RandomBag(int length) {
		if (length == 0) {
			elementData = EMPTY_ELEMENTDATE;
		} else if (length > 0) {
			elementData = new Object[length];
		} else {
			throw new RuntimeException("illegal argument:" + length);
		}
	}
	
	public void add(T item) {
		ensureCapacityInterval();
		elementData[size++] = item;
	}
	
	public void ensureCapacity(int capacity) {
		
		if (capacity < size || capacity > MAX_SIZE) {
			//所以只能是自己主动扩容时出错;
			throw new RuntimeException("illegal argument:" + capacity + ", atLeast:" + size);
		}
		
		int arrayLength = elementData.length;
		//当 size == 1 时  size + size >> 1 = 1;
		int expectLength = size + size >> 1 + 1;
		//在这里只能用这种方式, 如果用大于号直接比较, 当 expectLength > Integer.MAX_VALUE的时候, 同样为 false;
		//当目前容量足够, 则无需扩容;
		if (expectLength - arrayLength <= 0) {
			return;
		}
		
		//当容量已经等于 最大值, 内存溢出;
		if (size == MAX_SIZE) {
			throw new OutOfMemoryError();
		}
		//同上, 当数组长度为1 时;
		int expectedSizeMin = arrayLength + arrayLength >> 1 + 1;
		if (expectedSizeMin - MAX_SIZE > 0) {
			expectedSizeMin = MAX_SIZE;
		}
		if (capacity < expectedSizeMin ) {
			capacity = expectedSizeMin;
		}
		dilatation(capacity);
	}
	
	private void dilatation(int capacity) {
		//欠考虑, 当数组长度大于 Integer.MaxValue(目前已纠正)
		Object[] newArray = new Object[capacity];
		System.arraycopy(elementData, 0, newArray, 0, elementData.length);
		elementData = newArray;
	}
	
	//内部默认执行容量确认
	private void ensureCapacityInterval() {
		
		int capacity = 0;
		if (elementData == EMPTY_ELEMENTDATE) {
			capacity = DEFAULT_CAPACITY;
		} else {
			capacity = elementData.length;
		}
		ensureCapacity(capacity);
	}
	
	public static void main(String[] args) {
		
		new RandomBag().test();
	}
	
	public void test() {
		int a = MAX_SIZE;
		int b = a + 8;
		System.out.println(b - a);
	}
	
}
