package zyx.algorithms4th.unitone;

public class UnitOneExercisePointFive {

	public static void main(String[] args) {
		e_1();
	}
	
	private static void e_1() {
		//QuickFind qf = new QuickFind(10);
		//QuickUnion2 qf = new QuickUnion2(10);
		//QuickUnion qf = new QuickUnion(10);
		RoadQuickUnion qf = new RoadQuickUnion(10);
		String input = "9-2, 3-4, 5-8, 7-2, 2-1, 5-7, 0-3, 4-2";
		int[][] array = dealWithInput(input);
		for (int i = 0, length = array.length; i < length; i++) {
			if (!qf.connected(array[i][0], array[i][1])) {
				qf.union(array[i][0], array[i][1]);
			}
			System.out.println(qf);
			System.out.println(qf.times);
		}
	}
	
	private static class QuickFind implements IQuickFindUnion {
		
		private int[] array;
		int count;
		int times;
		
		QuickFind(int N) {
			array = new int[N];
			for (int i = 0; i < N; i++) {
				array[i] = i;
			}
			count = N;
		}
		
		@Override
		public int find(int p) {
			// TODO Auto-generated method stub
			times++;
			return array[p];
		}

		@Override
		public boolean connected(int p, int q) {
			// TODO Auto-generated method stub
			return find(p) == find(q);
		}

		@Override
		public void union(int p, int q) {
			// TODO Auto-generated method stub
			int length = array.length;
			int pRoot = find(p);
			int qRoot = find(q);
			if (qRoot == pRoot) {
				return;
			}
			for (int i = 0; i < length; i++) {
				times++;
				if (array[i] == qRoot) {
					array[i] = pRoot;
				}
			}
			count--;
		}

		@Override
		public int count() {
			// TODO Auto-generated method stub
			return count;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder str = new StringBuilder();
			for (int i = 0, length = array.length; i < length; i++) {
				str.append("" + array[i]);
			}
			return str.toString();
		}
	}
	
	private static class QuickUnion implements IQuickFindUnion {
		
		int array[];
		int count;
		int times;
		
		public QuickUnion(int n) {
			array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = i;
			}
			count = n;
		}
		
		@Override
		public int find(int p) {
			// TODO Auto-generated method stub
			while (true) {
				if (array[p] == p) {
					times++;
					return p;
				}
				p = array[p];
			}
		}
		@Override
		public boolean connected(int p, int q) {
			// TODO Auto-generated method stub
			return find(p) == find(q);
		}
		@Override
		public void union(int p, int q) {
			// TODO Auto-generated method stub
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) {
				return;
			}
			array[rootP] = rootQ;
			count--;
			times++;
		}
		@Override
		public int count() {
			// TODO Auto-generated method stub
			return count;
		}
		
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder str = new StringBuilder();
			for (int i = 0, length = array.length; i < length; i++) {
				str.append("" + array[i]);
			}
			return str.toString();
		}
		
		
	}
	
	private static class QuickUnion2 implements IQuickFindUnion {

		int[] array;
		int count;
		int times;
		int size[];
		
		public QuickUnion2(int n) {
			array = new int[n];
			size = new int[n];
			count = n;
			for (int i = 0; i < n; i++) {
				array[i] = i;
				size[i] = 1;
			}
		}
		
		@Override
		public int find(int p) {
			// TODO Auto-generated method stub
			while (true) {
				if (array[p] == p) {
					times++;
					return p;
				}
				p = array[p];
			}
		}

		@Override
		public boolean connected(int p, int q) {
			// TODO Auto-generated method stub
			return find(p) == find(q);
		}

		@Override
		public void union(int p, int q) {
			// TODO Auto-generated method stub
			int qRoot = find(q);
			int pRoot = find(p);
			int qSize = size[qRoot];
			int pSize = size[pRoot];
			if (qRoot == pRoot) {
				return;
			}
			
			if (qSize >= pSize) {
				array[pRoot] = qRoot;
				times++;
				qSize += pSize;
			} else {
				array[qRoot] = pRoot;
				times++;
				pSize += qSize;
			}
		}

		@Override
		public int count() {
			// TODO Auto-generated method stub
			return count;
		}
		
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder str = new StringBuilder();
			for (int i = 0, length = array.length; i < length; i++) {
				str.append("" + array[i]);
			}
			return str.toString();
		}
	}
	
	//1.5.12
	private static class RoadQuickUnion implements IQuickFindUnion {
		
		int array[];
		int count;
		int times;
		
		public RoadQuickUnion(int n) {
			array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = i;
			}
			count = n;
		}
		
		@Override
		public int find(int p) {
			// TODO Auto-generated method stub
			int temp = p;
			int result;
			while (true) {
				if (array[p] == p) {
					times++;
					result = p;
					break;
				}
				p = array[p];
			}
			p = result;
			while(true) {
				if (array[p] == result) {
					return result;
				}
				temp = p;
				array[temp] = result;
				p = array[p];
			}
		}
		@Override
		public boolean connected(int p, int q) {
			// TODO Auto-generated method stub
			return find(p) == find(q);
		}
		@Override
		public void union(int p, int q) {
			// TODO Auto-generated method stub
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) {
				return;
			}
			array[rootP] = rootQ;
			count--;
			times++;
		}
		@Override
		public int count() {
			// TODO Auto-generated method stub
			return count;
		}
		
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder str = new StringBuilder();
			for (int i = 0, length = array.length; i < length; i++) {
				str.append("" + array[i]);
			}
			return str.toString();
		}
		
	}
	
	private static int[][] dealWithInput(String input) {
		String[] temp = input.split(",");
		int[][] array = new int[temp.length][2];
		String tempStr, tempArray[] = new String[2];
		for (int i = 0, length = temp.length; i < length; i++) {
			tempStr = temp[i].trim();
			tempArray = tempStr.split("-");
			array[i][0] = Integer.parseInt(tempArray[0]);
			array[i][1] = Integer.parseInt(tempArray[1]);
		}
		return array;
	}
}
