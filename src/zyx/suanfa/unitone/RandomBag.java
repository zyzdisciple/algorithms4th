package zyx.suanfa.unitone;

public class RandomBag<T> {

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
	
	public void ensureCapacity(int capacity) {
		
		if (capacity < size || capacity > MAX_SIZE) {
			//����ֻ�����Լ���������ʱ����;
			throw new RuntimeException("illegal argument:" + capacity + ", atLeast:" + size);
		}
		
		int arrayLength = elementData.length;
		//�� size == 1 ʱ  size + size >> 1 = 1;
		int expectLength = size + size >> 1 + 1;
		//������ֻ�������ַ�ʽ, ����ô��ں�ֱ�ӱȽ�, �� expectLength > Integer.MAX_VALUE��ʱ��, ͬ��Ϊ false;
		//��Ŀǰ�����㹻, ����������;
		if (expectLength - arrayLength <= 0) {
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
		
		new RandomBag().test();
	}
	
	public void test() {
		int a = MAX_SIZE;
		int b = a + 8;
		System.out.println(b - a);
	}
	
}
