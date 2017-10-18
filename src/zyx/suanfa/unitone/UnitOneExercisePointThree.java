package zyx.suanfa.unitone;

public class UnitOneExercisePointThree {

	 public class FixedCapacityStackOfObjects<Item> {
		
		private int size;
		private Object[] array;
		
		public FixedCapacityStackOfObjects(int n) {
			array = new Object[n];
		}
		
		public void push(Item item) {
			
			if (size < array.length) {
				array[size++] = item;
			} else {
				throw new RuntimeException("存入数据过多， 栈溢出");
			}
		}
		
		@SuppressWarnings("unchecked")
		public Item pop() {
			
			if (size > 0) {
				return (Item) array[--size];
			} else {
				throw new RuntimeException("数据已经全部取出");
			}
		}
		
		public boolean isEmpty() {
			
			return size > 0;
		}
		
		public int size() {
			
			return size;
		}
	}
	
	 public static void main(String[] args) {
		
		 
	}
}
