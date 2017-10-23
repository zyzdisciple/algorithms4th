package zyx.suanfa.unitone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomBag<T> implements Iterable<T> {

	//��java ����������ת�ͺ�����ת�͵Ĵ���,ͬʱ����֮��ת������һ��������,java��֧�ַ�������, ��Ȼ��
	//֧�ַ�������, ����Ͳ����巺������.����ȡ��Ԫ�ص�ʱ��, ת��Ϊ��Ӧ�����ͼ���;�ڷ����Ĳ������ݹ�����,�Ѿ�
	//���Զ����ͽ����޶�;
	//private T[] elementData;
	private Object[] elementData;
	
	private int size;
	
	private static final int DEFAULT_CAPACITY = 16;
	
	//�ο�ArrayList, Ϊ��ʹ�����еĿ����鹲ָͬ��ͬһ����ַ, ���峣��;
	private static final Object[] EMPTY_ELEMENTDATE = {};
	
	//����ArrayList����, ĳЩJVM���� arrayǰ����ͷ,�ᵼ��outOfMemory
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
			//����ֻ�����Լ���������ʱ����;
			throw new RuntimeException("illegal argument:" + capacity + ", atLeast:" + size);
		}
		
		int arrayLength = elementData.length;
		//�� size == 1 ʱ  size + size >> 1 = 1;
		int expectLength = size + size >> 1 + 1;
		System.out.println(arrayLength);
		System.out.println(expectLength);
		//������ֻ�������ַ�ʽ, ����ô��ں�ֱ�ӱȽ�, �� expectLength > Integer.MAX_VALUE��ʱ��, ͬ��Ϊ false;
		//��Ŀǰ�����㹻, ����������;
		//��  size ��length = 0,ʱ ����Ϊ ��
		if (expectLength - arrayLength <= 0 && arrayLength != 0) {
			return;
		}
		
		//�������Ѿ����� ���ֵ, �ڴ����;
		if (size == MAX_SIZE) {
			throw new OutOfMemoryError();
		}
		//ͬ��, �����鳤��Ϊ1 ʱ;
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
		//Ƿ����, �����鳤�ȴ��� Integer.MaxValue(Ŀǰ�Ѿ���)
		Object[] newArray = new Object[capacity];
		System.arraycopy(elementData, 0, newArray, 0, elementData.length);
		elementData = newArray;
	}
	
	//�ڲ�Ĭ��ִ������ȷ��
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
		 * ��ArrayList�ж�������һ��Ԫ��, ��Random�����趨��, ��Ϊÿ��ȡ�������ȡ��;
		 * 
		 * */
		
		public RandomBagIterator() {
			//��Ϊ�� Bag�н����Ǳ���ȡ��Ԫ��, ȴ���Բ�ɾ��Ԫ��, �����Ҫ�½� ArrayList,
			/*
			 * �� asList�ĵײ�ʵ��, ��������һ�� ����ǰ array������ ��ֵ����һ��, ��������list�Ĳ�������ı�ԭ����;
			 * Arrays.asList ���ص� Ϊ  java.util.Arrays.ArrayList extends AbstractList ����ʵ���˶�Ӧ�Ĳ��ַ���;
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
