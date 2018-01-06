package zyx.algorithms4th.unitthree;

import java.util.Iterator;

/**
 * 二叉查找树
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
		
		while (temp != null) {
			cmp = key.compareTo(temp.key);
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
		
		/**
		 * 如果未命中, 直接返回
		 * 如果删除的节点为根节点, 直接删除
		 * 如果删除的节点为叶节点, 需要找到对应的父级节点, 将其指向置空
		 * 如果非根节点/叶节点, 则需要找到左/右 树的最大/最小节点, 将置换后删除
		 * 在这里我取 被删除节点的左子节点的最大子节点替换.
		 */
		if (parentNode == null)
			return;
		int cmp = key.compareTo(parentNode.key);
		boolean flag;
		Node temp;
		
		if (cmp > 0) {
			deleteNode = parentNode.right;
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
		
		while (true) {
			if (cmp > 0) {
				child = parent.right;
			} else if (cmp < 0) {
				child = parent.right;
			} else {
				return parent;
			}
			//表示查找未命中
			if (child == null) {
				return null;
			}
			
			cmp = key.compareTo(child.key);
			parent = child;
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

	@Override
	public int rank(Key min, Key max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Key select(int k) {
		
		if (root == null || root.N < k) {
			return null;
		}
		
		Node parent = root, lf, rg;
		int cmp;
		
		while (parent != null) {
			lf = parent.left;
			rg = parent.right;
			if (lf != null) {
				cmp = lf.N + 1;
				if (cmp > k) {
					parent = lf;
					break;
				} else if (cmp == k) {
					return parent.key;
				} else {
					k -= cmp;
					parent = rg;
				}
			} else {
				if (k == 1) {
					return parent.key;
				} else {
					k -= 1;
					parent = rg;
				}
			}
		}
		return null;
	}

	@Override
	public Iterable<Key> keys() {
		// TODO Auto-generated method stub
		return null;
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
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class KeysIterator implements Iterator<Key> {
		
		private Key[] keys;
		
		private int count;
		
		@SuppressWarnings("unchecked")
		public KeysIterator() {
			count = 0;
			if (isEmpty()) {
				keys = (Key[]) new Object[1];
				keys[0] = null;
			}
			
			keys = (Key[]) new Object[root.N];
			Node lf, parent = root, rg, current;
			for (int i = 0, length = root.N, temp = length; i < length; i++) {
				lf = parent.left;
				if (lf != null) {
					
				}
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Key next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
