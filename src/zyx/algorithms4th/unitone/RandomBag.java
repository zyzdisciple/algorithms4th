package zyx.algorithms4th.unitone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomBag<T> implements Iterable<T> {

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
	
	public boolean isEmpty() {
		
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void ensureCapacity(int capacity) {
		
		if (capacity < size || capacity > MAX_SIZE) {
			//所以只能是自己主动扩容时出错;
			throw new RuntimeException("illegal argument:" + capacity + ", atLeast:" + size);
		}
		
		int arrayLength = elementData.length;
		//当 size == 1 时  size + size >> 1 = 1;
		int expectLength = size + size >> 1 + 1;
		System.out.println(arrayLength);
		System.out.println(expectLength);
		//在这里只能用这种方式, 如果用大于号直接比较, 当 expectLength > Integer.MAX_VALUE的时候, 同样为 false;
		//当目前容量足够, 则无需扩容;
		//当  size 和length = 0,时 条件为 真
		if (expectLength - arrayLength <= 0 && arrayLength != 0) {
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
		
		RandomBag<String> bag = new RandomBag<>(0);
		bag.add("abc");
		
	}
	
	public void test() {
		int a = MAX_SIZE;
		int b = a + 8;
		System.out.println(b - a);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new RandomBagIterator<T>();
	}
	
	private class RandomBagIterator<T> implements Iterator<T> {
		
		List<Object> list;
		int listSize;
		
		/*
		 * 在ArrayList中定义有上一个元素, 在Random中无需定义, 因为每次取都是随机取出;
		 * 
		 * */
		
		public RandomBagIterator() {
			//因为在 Bag中仅仅是遍历取出元素, 却绝对不删除元素, 因此需要新建 ArrayList,
			/*
			 * 在 asList的底层实现, 仅仅是另一个 将当前 array的引用 赋值给另一个, 所以所有list的操作都会改变原数据;
			 * Arrays.asList 返回的 为  java.util.Arrays.ArrayList extends AbstractList 重新实现了对应的部分方法;
			 * 
			 * */
			list = new ArrayList<> (Arrays.asList(elementData));
			listSize = list.size();
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return listSize != 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			// TODO Auto-generated method stub
			Collections.shuffle(list);
			T item = (T) list.get(0);
			list.remove(0);
			return item;
		}
		
	}
	
}
