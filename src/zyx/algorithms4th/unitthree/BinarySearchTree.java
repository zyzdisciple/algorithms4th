package zyx.algorithms4th.unitthree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 * keys方法没有实现 2018年1月8日12:05:29
 * @author Administrator
 *
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
	
	private Node root;
	
	private class Node {
		
		private Node left;
		
		private Node right;
		
		private Key key;
		
		private Value value;
		
		private int N;
		
		public Node (Key key, Value value, int N) {
			
			this.key = key;
			this.value = value;
			this.N = N;
		}
		
		@Override
		public String toString() {
			return key == null ? null : key.toString();
		}
	}
	
	
	@Override
	public Value get(Key key) {
		if (key == null)
			throw new RuntimeException("键值不能为null");
		
		Node tempNode = getNode(key);

		return tempNode == null ? null : tempNode.value;
	}

	@Override
	public void put(Key key, Value value) {
		if (key == null)
			throw new RuntimeException("键值不能为null");
		
		Node newNode = new Node(key, value, 1);
		if (root == null) {
			root = newNode;
			return;
		}
		Node parent = root, child;
		int cmp;
		boolean flag; //表示要插入左 true, 右 false;
		
		while (true) {
			cmp = key.compareTo(parent.key);
			if (cmp > 0) {
				child = parent.right;
				flag = false;
			} else if (cmp < 0) {
				child = parent.left;
				flag = true;
			} else {
				parent.value = value;
				return;
			}
			
			if (child == null) {
//代码用来测试当前放入节点 及其 父节点, 和 在节点左右位置;
System.out.println("currentNode: " + newNode);
System.out.println("parentNode: " + parent);
System.out.println("**************");
				if (flag) {
					parent.left = newNode;
				} else {
					parent.right = newNode;
				}
				break;
			}
			
			parent = child;
		}
		
		updateN(key, 1);
	}
	
	/**
	 * 从根节点到当前节点为止, n均改变count, 不包括当前节点
	 * @param key
	 * @param count 改变值 1 或 -1
	 */
	private void updateN(Key key, int count) {
		
		Node temp = root;
		int cmp;
//System.out.println("**********************************");
//System.out.println("当前节点:" + key);
//System.out.println("**********************************");
		while (temp != null) {
			cmp = key.compareTo(temp.key);
//System.out.println("Node: " + temp + " N: " + temp.N);
			if (cmp > 0) {
				temp.N += count;
				temp = temp.right;
			} else if (cmp < 0) {
				temp.N += count;
				temp = temp.left;
			} else {
				return;
			}
		}
	}

	/**
	 * 存在问题, 怎样去表示删除一个节点, 将当前节点 key设置为null, 还是将 node设置为null;
	 * 解决方案, 需要将 被删除节点的 父节点指向空节点/另一个节点.
	 */
	@Override
	public void delete(Key key) {
		if (key == null) {
			throw new NullPointerException();
		}
		
		//找出需要被删除的节点及其父节点
		Node parentNode = getParentNode(key), deleteNode;
//System.out.println("parentNode:" + parentNode);		
		/**
		 * 如果未命中, 直接返回
		 * 如果删除的节点为根节点, 直接删除
		 * 如果删除的节点为叶节点, 需要找到对应的父级节点, 将其指向置空
		 * 如果非根节点/叶节点, 则需要找到左/右 树的最大/最小节点, 将置换后删除
		 * 在这里我取 被删除节点的左子节点的最大子节点替换.
		 */
		if (parentNode == null) {
			return;
		}
		int cmp = key.compareTo(parentNode.key);
		boolean flag;
		Node temp;
		
		if (cmp > 0) {
			deleteNode = parentNode.right;
//System.out.println("deleteNode:" + deleteNode.key);
			if (isLeaf(deleteNode)) { //如果是叶节点, 直接删除即可.
				updateN(deleteNode.key, -1); //先更新N后删除
				parentNode.right = null;
				return;
			}
			if (deleteNode.left == null) {
				temp = deleteNode.right;
			} else {
				temp = deleteMax(deleteNode.left);
			}
			flag = true;
		} else if (cmp < 0) {
			deleteNode = parentNode.left;
//System.out.println("deleteNode:" + deleteNode.key);
			if (isLeaf(deleteNode)) {
				parentNode.left = null;
				return;
			}
			if (deleteNode.left == null) {
				temp = deleteNode.right;
			} else {
				temp = deleteMax(deleteNode.left);
			}
			flag = false;
		} else {
			//表示当前处于根节点
//System.out.println("root节点");
			root = null;
			return;
		}
		
		temp.left = deleteNode.left;
		temp.right = deleteNode.right;
		temp.N = deleteNode.N;
		if (flag) {
			parentNode.right = temp;
		} else {
			parentNode.left = temp;
		}
	}
	
	/**
	 * 在这里获取 key 对应的父节点, 如果key 对应根节点, 父节点也为 root
	 * @param key
	 * @return
	 */
	private Node getParentNode(Key key) {
		
		if (root == null) {
			return null;
		}
		
		Node parent = root, child;
		int cmp = key.compareTo(root.key);
		
		if (cmp > 0) {
			child = parent.right;
		} else if (cmp < 0) {
			child = parent.left;
		} else {
			return parent;
		}
		
		while (true) {
			//表示查找未命中
			if (child == null) {
				return null;
			}
			cmp = key.compareTo(child.key);
			
			if (cmp > 0) {
				parent = child;
				child = parent.right;
			} else if (cmp < 0) {
				parent = child;
				child = parent.left;
			} else {
				return parent;
			}
		}
	}
	
	private boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}
	
	private Node getNode(Key key) {
		Node temp = root;
		int cmp;
		
		while (temp != null) {
			cmp = key.compareTo(temp.key);
			if (cmp > 0) {
				temp = temp.right;
			} else if (cmp < 0) {
				temp = temp.left;
			} else {
				return temp;
			}
		}
		return null;
	}

	@Override
	public boolean contains(Key key) {
		if (key == null) {
			return false;
		}
		
		Node temp = root;
		int cmp;
		while (temp != null) {
			cmp = key.compareTo(temp.key);
			if (cmp > 0) {
				temp = temp.right;
			} else if (cmp < 0) {
				temp = temp.left;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		
		return root == null;
	}

	@Override
	public int size() {
		
		return size(root);
	}
	
	private int size(Node node) {
		
		if (node == null) {
			return 0;
		}
		return node.N;
	}

	/**
	 * 在这里我觉得还可以将两个循环合并起来, 只给出了简化版.
	 */
	@Override
	public int rank(Key min, Key max) {
		
		if (min == null || max == null) {
			throw new RuntimeException("键值不能为空");
		}
		
		if (root == null || min.compareTo(max) >= 0) {
			return 0;
		}
		
		Node lower = root, higher = root;
		int cmpMin, cmpMax, lowerCount = 0, higherCount = 0, temp;
		
		while (lower != null) {
			
			//通过这一步循环, 找出了小于 min的节点的总数.
			cmpMin = min.compareTo(lower.key);
			if (cmpMin < 0) { //当前节点小于最小值,
				lower = lower.left;
			} else { //当前节点小于最小值, 则选取右节点进行比较, 直到为null.
				if (lower.left != null) {
					temp = lower.left.N + 1;
				} else {
					temp = 1;
				}
				lowerCount += temp;
				lower = lower.right;
			}
		}
		
		while (higher != null) {
			
			cmpMax = max.compareTo(higher.key);
			if (cmpMax > 0) {
				higher = higher.right;
			} else {
				if (higher.right != null) {
					temp = higher.right.N + 1;
				} else {
					temp = 1;
				}
				higherCount += temp;
				higher = higher.left;
			}
		}
		
		return root.N - lowerCount - higherCount;
	}

	@Override
	public Key select(int k) {
		
		if (root == null || root.N < k) {
			return null;
		}
		
		Node parent = root, lf, rg;
		int cmp;
		
		//只要 root != null 和 root.N >= k必然会找到对应的节点. 否则的话,就是代码有问题.
		while (true) {
			lf = parent.left;
			rg = parent.right;
			if (lf != null) {
				cmp = lf.N + 1; //parent节点的序列
				if (cmp > k) {
					parent = lf;
				} else if (cmp == k) {
					return parent.key;
				} else {
					k -= cmp; //将当前parent的序列置为0
					parent = rg;
				}
			} else {// 左子树为空, 此时 如果右子树也为空,则 k只能等于1;
				if (k == 1) { //如果k不为1, 则右子树必然不为空
					return parent.key;
				} else {
					k -= 1;	//将当前parent 置为0;
					parent = rg;
				}
			}
		}
	}

	@Override
	public Iterable<Key> keys() {
		return () -> new KeysIterator();
	}

	@Override
	public Key max() {
		return root == null ? null : max(root).key;
	}
	
	private Node max(Node node) {
		
		Node temp = node;
		
		while (temp.right != null) {
			temp = temp.right;
		}
		
		return temp;
	}

	@Override
	public Key min() {
		
		return root == null ? null : min(root).key;
	}
	
	private Node min(Node node) {
		
		Node temp = node;
		
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}

	@Override
	public Key floor(Key key) {
		
		if (key == null) {
			throw new NullPointerException();
		}
		
		Node temp = root, min = null;
		int cmp;
		while (temp != null) {
			cmp = key.compareTo(temp.key);
			if (cmp < 0) {
				temp = temp.left;
				min = temp;
			} else {
				break;
			}
		}
		
		return min == null ? null : min.key;
	}

	@Override
	public Key ceiling(Key key) {
		
		if (key == null) {
			throw new NullPointerException();
		}
		
		Node temp = root, max = null;
		int cmp;
		while (temp != null) {
			cmp = key.compareTo(temp.key);
			if (cmp > 0) {
				temp = temp.right;
				max = temp;
			} else {
				break;
			}
		}
		
		return max == null ? null : max.key;
	}

	@Override
	public void deleteMax() {
		
		if (root == null || isLeaf(root)) {
			root = null;
			return;
		}
		
		deleteMax(root);
		
	}
	
	/**
	 * 返回当前节点下最大节点, 包括它本身
	 * 删除当前节点下的最大子节点. 如果它自己为最大节点, 无法删除, 因为需要父节点信息.
	 * 这里仅仅需要将被置换的节点, 删除并返回即可.
	 * @param node
	 * @return
	 */
	private Node deleteMax(Node node) {
		
		if (node.right == null) {
			updateN(node.key, -1);
			return node;
		}
		
		Node parent = node, child;
		
		while (true) {
			child = parent.right;
			if (child.right == null) {
				//说明当前child为最大节点
				//无论当前是否是叶节点, 都令其指向, child.left;
				/*
				 * 几次陷入一个误区, 非叶节点的话, 找child 的左节点的最大节点, 然而最终的目的
				 * 仅仅是找到 node 的最大节点, 其实这个时候已经达到目的了.
				 */
				parent.right = child.left;
				updateN(child.key, -1);
				return child;
			}
			parent = child.right;
		}
	}

	@Override
	public void deleteMin() {
		
		if (root == null || isLeaf(root)) {
			root = null;
			return;
		}
		
		deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		
		if (node.left == null) {
			updateN(node.key, -1);
			return node;
		}
		
		Node parent = node, child;
		
		while (true) {
			child = parent.left;
			if (child.left == null) {
				parent.left = child.right;
				updateN(child.key, -1);
				return child;
			}
			parent = child.left;
		}
	}

	@Override
	//未实现, 在不储存父级节点的情况下, 用迭代的方式去遍历一棵树稍显困难.
	/**
	 * 如果用递归实现, 思路较简单, 遍历左子树,直到左子树为空  再遍历右子树, 不要忘记根节点即可.
	 */
	public Iterator<Key> iterator() {
		return new KeysIterator();
	}
	
	private class KeysIterator implements Iterator<Key> {
		
		/**储存每一级的父节点. 这里数组长度等于树高度, 直接假定树高度不超过100,
		 * 在这里更好的方式是通过方法获取树高度, 或是, 用链表来解决.
		 */
		private Object[] parents = new Object[100];
		//current表示当前数组中存有的 父节点的总数, 叶节点也会被当做父节点, 同时用于定位当前currentNode所在树的高度
		private int current = 0, count;
		private ParentNode currentNode;
		
		public KeysIterator() {
			
			Node temp = root;
			ParentNode pTemp;
			for (int i = 0; temp != null; ++i, temp = temp.left, ++current) {
				pTemp = new ParentNode(temp);
				parents[i] = currentNode = pTemp;
			}
			current--;	//使得current指向当前节点.
		}

		@Override
		public boolean hasNext() {
			
			return currentNode != null;
		}

		/**
		 * 找到最末级的左节点, 节点取出次序, 左子节点 -> 根节点 -> 右节点的最末级左子节点
		 * ->根节点的父级节点 -> 父级节点的右节点的最末级节点
		 * 同时维持一个 flag表示当前节点的左子树是否被遍历.
		 * 问题: 在什么时候会深入一棵树, 在向上走一级的时候, 再度向下遍历
		 * 结束: 1:加入变量 count表示当前已遍历节点数.当 == root.N时结束.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Key next() {
			
			Node result = currentNode.node;
			currentNode.flag = false; //返回当前节点,表示已经被遍历过
			count++;
			System.out.println("currentNode: " + currentNode + " ,current:" + current);
			
			if (count == root.N) {
				currentNode = null;
				return null;
			}
			
			ParentNode pTemp, cTemp = currentNode;
			pTemp = (BinarySearchTree<Key, Value>.ParentNode) parents[current + 1];
			
			//在当前节点只有两种可能, 向上, 或向右(即向下).
			if (pTemp == null) {//null表示当前右子树未遍历过,且树高度,超出当前节点高度.
				goDeepTree(cTemp, false);
				if (cTemp == currentNode) {//如果相等表示当前节点为叶节点, 需要向上.
					upTree();
				}
			} else if (pTemp.flag) {//flag为true表示本次需要向下走, 且走到最末端.
				goDeepTree(cTemp, false);
				
			} else {//为false表示当前节点被遍历过
				upTree();
			}
			
			return result.key;
		}
		
		/**
		 * 深入这棵树, 且将currentNode置为当前树的最小子节点
		 * @param pNode
		 * @param onlyTraversal 如果为true, 仅遍历树, 但不改变 currentNode, 否则改变currentNode
		 */
		@SuppressWarnings("unchecked")
		private void goDeepTree(ParentNode cNode, boolean onlyTraversal) {
			Node rNode = cNode.node.right;
			int i = current;
			
			while (rNode != null) {
				parents[++i] = new ParentNode(rNode);
				rNode = rNode.left;
			}
			
			if (onlyTraversal) {
				return;
			} else {
				currentNode = (BinarySearchTree<Key, Value>.ParentNode) parents[current = i];
			}
			
		}
		
		@SuppressWarnings("unchecked")
		private void upTree() {
			Node temp;
			
			ParentNode pTemp = (BinarySearchTree<Key, Value>.ParentNode) parents[current];
			while (!pTemp.flag) { //只要当前节点为false, 就需要一直向上,且在每次向上的时候检查对应的右节点, 直到,遇到为true的;
				temp = pTemp.node;
				pTemp = (BinarySearchTree<Key, Value>.ParentNode) parents[--current];
				if (temp == pTemp.node.right) {
					parents[current + 1] = null; //将回退的节点变为空
					continue;
				} else {
					currentNode = pTemp; //同时遍历这棵树的右子树
					goDeepTree(currentNode, true);
					return;
				}
				
			}
			
		}
	}
	
	private class ParentNode {
		private Node node;
		
		private boolean flag;
		
		public ParentNode(Node node) {
			this.node = node;
			this.flag = true;
		}
		
		@Override
		public String toString() {
			return "node: " + node;
		}
	}
	
	/**
	 * 说明, 以下部分均为习题内容
	 */
	@Override
	public boolean isBinaryTree() {
		if (root == null) {
			return true;
		}
		return root == null ? true : (root.N == isBinaryTree(root));
	}
	
	private int isBinaryTree(Node node) {
		int count;
		if (node == null) {
			return 0;
		}
		count = isBinaryTree(node.left) + isBinaryTree(node.right) + 1;
		return count;
	}
	
	@Override
	public boolean isOrdered() {
		if (root == null || isLeaf(root)) {
			return true;
		}
		return isOrdered(root, min(), max());
	}
	
	private boolean isOrdered(Node node, Key min, Key max) {
		
		if (isLeaf(node)) {
			return true;
		}
		
		boolean flag;
		Node lf = node.left, rg = node.right;
		
		if (lf == null) {
			flag = min.compareTo(node.key) <= 0 && max.compareTo(rg.key) >= 0 && node.key.compareTo(rg.key) < 0;
			return flag && isOrdered(rg, min(rg).key, max(rg).key);
		}
		
		if (rg == null) {
			flag = min.compareTo(lf.key) <= 0 && max.compareTo(node.key) >= 0 && node.key.compareTo(lf.key) > 0;
			return flag && isOrdered(lf, min(lf).key, max(lf).key);
		}
		
		flag = min.compareTo(lf.key) <= 0 && max.compareTo(rg.key) >= 0 && node.key.compareTo(lf.key) > 0 && node.key.compareTo(rg.key) < 0;
		return flag && isOrdered(lf, min(lf).key, max(lf).key) && isOrdered(rg, min(rg).key, max(rg).key);
	}
	
	@Override
	public boolean hasNoDuplicates() {
		if (root == null || isLeaf(root)) {
			return true;
		}
		return hasNoDuplicates(root);
	}
	
	private boolean hasNoDuplicates(Node node) {
		if (isLeaf(node)) {
			return true;
		}
		
		boolean flag;
		Node lf = node.left, rg = node.right;
		Key key = node.key;
		
		if (lf == null) {
			flag = !key.equals(rg.key);
			return flag && hasNoDuplicates(rg);
		}
		
		if (rg == null) {
			flag = !key.equals(lf.key);
			return flag && hasNoDuplicates(lf);
		}
		
		flag = !(key.equals(lf.key) && key.equals(rg.key));
		return flag && hasNoDuplicates(lf) && hasNoDuplicates(rg);
	}
	
	public boolean isBST() {
		if (isBinaryTree() && isOrdered() && hasNoDuplicates()) {
			return true;
		}
		return false;
	}
	
	//3.2.37
	public void printLevel() {
		
		printLevel(root);
	}
	
	private void printLevel(Node node) {
		
		Queue<Node> list = new LinkedList<Node>();
		list.add(node);
		while (list.peek() != null) {
			list = pushAndPrint(list);
			System.out.println();
		}
		
	}
	
	private Queue<Node> pushAndPrint(Queue<Node> container) {
		Queue<Node> qTemp = new LinkedList<Node> ();
		Node temp, lf, rg;
		while ((temp = container.poll()) != null) {
			System.out.print(temp + ", ");
			lf = temp.left;
			rg = temp.right;
			if (lf != null) {
				qTemp.add(lf);
			}
			if (rg != null) {
				qTemp.add(rg);
			}
		}
		return qTemp;
	}
}
