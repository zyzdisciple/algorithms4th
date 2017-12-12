package zyx.algorithms4th.unittwo;

public class UnitTwoPointThreeExercises {

	public class Sort235<T> extends AbstractSort<T> {

		@SuppressWarnings("unchecked")
		@Override
		public void sort() {
			// TODO Auto-generated method stub
			T v = (T) array[0];
			
			int lo = 0, rg = array.length;
			//通过这种方式不需要判断界限
			while(array[++lo].compareTo(v) == 0 && lo < rg)
				;
			int cmp = array[lo--].compareTo(v);
			
			if (lo >= rg) {
				return;
			}
			
			if (cmp > 0) {
				while (true) {
					while(array[++lo].compareTo(v) == 0)
						;
					
					while (array[--rg].compareTo(v) != 0)
						;
					
					if (lo >= rg)
						break;
					
					exchange(lo, rg);
					
				}
			}  else {
				lo = -1;
				while (true) {
					
					while (array[++lo].compareTo(v) != 0)
						;
					
					while (array[--rg].compareTo(v) == 0)
						;
					
					if (lo >= rg)
						break;
					
					exchange(lo, rg);
				}
			}
		}
		
	}
}
