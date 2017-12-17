package zyx.algorithms4th.unittwo;

//未测试
public class MaxPriorityQueueWithLink<Key extends Comparable<Key>> {
	
	private Node root;
	
	//末节点
	private Node leaf;
	
	private int size;

	public MaxPriorityQueueWithLink() {
		
	}
	
	public MaxPriorityQueueWithLink(Key[] array) {
		
		for (int i = 0, length = array.length; i < length; i++) {
			insert(array[i]);
		}
	}
	
	public Key max() {
		
		return root.item;
	}
	
	public Key delMax() {
		
		Key key = root.item;
		exchange(root, leaf);
		
		Node parent = leaf.upNode;
		leaf = null;
		if (parent != null) {
			if (hasLeaf(parent)) {
				leaf = parent.leftNode != null ? parent.leftNode : parent.rightNode;
			} else {
				leaf = parent;
			}
		}
		
		size--;
		sink(root);
		return key;
	}
	
	public void insert(Key key) {
		
		Node newNode = new Node(key);
		
		if (size == 0) {
			root = leaf = newNode;
			size ++;
			return;
		}
		
		Node parent = leaf.upNode;
		
		if (parent == null) {
			leaf = root.leftNode = newNode;
			leaf.upNode = root;
		}
		
		if (leaf == parent.leftNode) {
			leaf = parent.rightNode = newNode;
		} else {
			Node temp = leaf;
			leaf = leaf.leftNode = newNode;
			leaf.upNode = temp;
		}
		size++;
		swim(leaf);
	}
	
	public int size() {
		
		return size;
	}
	
	public boolean isEmpty() {
		
		return size == 0;
	}
	
	private void swim(Node k) {
		
		Node up;
		while ((up = k.upNode) != null && less(up, k)) {
			exchange(up, k);
			k = up;
		}
	}
	
	private void sink(Node k) {
		
		Node comp;
		Node left;
		Node right;
		
		while ((left = k.leftNode) != null | (right = k.rightNode) != null  ) {
			if (less(left, right)) {
				comp = right;
			}  else {
				comp = left;
			}
			if (less(k, comp)) {
				exchange(k, comp);
				k = comp;
			}
		}
	}
	
	private boolean hasLeaf(Node k) {
		return k.leftNode == k.rightNode;
	}
	
	//被比较的两者不能同时为 null;
	private boolean less(Node p, Node q) {
		
		if (p == null) {
			return true;
		} else if (q == null) {
			return false;
		}
		
		if (p.item.compareTo(q.item) < 0) {
			return true;
		}
		return false;
	}
	
	private void exchange(Node p, Node q) {
		Key temp = p.item;
		p.item = q.item;
		q.item = temp;
	}
	
	private class Node {
		
		private Node upNode;
		
		private Node leftNode;
		
		private Node rightNode;
		
		private Key item;
		
		public Node(Key key) {
			this.item = key;
		}
		
	}
}
