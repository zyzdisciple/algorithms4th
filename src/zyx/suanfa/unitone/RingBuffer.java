package zyx.suanfa.unitone;

/**
 * �ѵ�: ��Ϊ����ǳ���������ȡ�Ļ�, ��֮ǰ��ʵ�ַ�ʽ��ͬ, ������ÿ�ν���(��ȡ����)����Ҫ copy ����, ��������, 
 * ������Ĵ�ȡƵ���ֻ��ر��, ���Լ�ʹ��ʵ��, ��������̫��
 * ��Ҫ�����ж������Ƿ�Ϊ�� ���Ѿ�����;
 * @author zyzdisciple
 *
 * @param <T>
 */
public class RingBuffer<T> {

	private Object[] elementData;
	private static final int DEFAULT_SIZE = 16;
	//ʵʱ��ʾ���鵱ǰ�Ĵ���,�����̵߳ȴ��򲻵ȴ�
	private volatile int size;
	//���������±�,��ʾ�����鵱ǰ����һ��λ�����
	private volatile int in;
	//��������±�, ��ʾ����ӵ�ǰ�ĸ�index ȡ��
	private volatile int out;
	
	//��ʼ��
	public RingBuffer() {
		
		elementData = new Object[DEFAULT_SIZE];
	}
	
	//���ܳ���, ��ʼ��
	public RingBuffer(int length) {
		
		if (length <= 0) {
			throw new RuntimeException("illegal argument: " + length);
		}
		elementData = new Object[length];
	}
	
	
	//�򻺳����������, ����ֵ��Ϊ boolean, ʹ��add�������������, ���� isFull()�Ĺ���
	public void add(T item) {
		
		if (isFull()) {
			throw new RuntimeException("����������");
		}
		elementData[in++] = item;
		ringIn();
		size++;
	}
	
	//ȡ������
	@SuppressWarnings("unchecked")
	public T get() {
		
		if (isEmpty()) {
			throw new RuntimeException("������Ϊ��");
		}
		T item =(T) elementData[out];
		elementData[out++] = null;
		ringOut();
		size--;
		return item;
	}

	//2017��10��25��08:45:47
	//����/����   ������
	public void resizeBuffer(int capacity) {
		int tempSize = size;
		if (capacity <= size) {
			throw new RuntimeException("�������ù�С");
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
	
	//��ǰ����������
	public int size() {
		
		return size;
	}
	
	//�������Ƿ�����
	public boolean isFull() {
		
		return size >= elementData.length;
	}
	
	//�������Ƿ�Ϊ��
	public boolean isEmpty() {
		
		return size <= 0;
	}
	
	//��ջ�����
	public void clear() {
		//ֱ��ɾ��ԭ���������, ���������;�������;
		Object[] tempArray = new Object[elementData.length];
		elementData = tempArray;
		in = out = size = 0;
	}
	
	private void ringIn() {
		//�������� in ����ĩβ, ��ʱin++Ϊ length, ��ָ������� 0 λ��
		if (in == elementData.length) {
			in = 0;
		}
	}
	
	private void ringOut() {
		//��outָ��ǰҪȡ�����ݵ�λ��, �� ָ��ĩβʱ, out++ ��Ϊ length, ��Ҫȥָ������Ŀ�ͷ;
		if (out == elementData.length) {
			out = 0;
		}
	}
	
	public static void main(String[] args) {
		RingBuffer<String> rb = new RingBuffer<>();
		int index = 0;
		for(int i = 0; i < 16; i++) {
			if (!rb.isFull()) {
				rb.add("���ǵ�" + index + "�����ĵ�һ�����");
				index++;
			}
		}
		
		for (int i = 0; i < 12; i++) {
			System.out.println(rb.get());
		}
		
		for(int i = 0; i < 12; i++) {
			if (!rb.isFull()) {
				rb.add("���ǵ�" + index + "��λ�õĵڶ������");
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
				rb.add("���ǵ�" + index + "��λ�õĵ��������");
				index++;
			}
		}
		
		rb.resizeBuffer(25);
		for (int i = 0; !rb.isEmpty(); i++) {
			System.out.println(rb.get());
		}
	}
}
