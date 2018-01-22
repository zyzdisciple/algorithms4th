package zyx.algorithms4th.unitthree;

import java.util.Iterator;
/**
 * 仅仅是自己手动实现红黑树对我而言已经不是一件简单的事了, 在这里, 就决定采取递归的方式来做这件事.
 * 因为红黑树的完美平衡性, 保证树高不会太高, 因此在递归中所带来的开销是可以接受的.
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackSearchTree<Key extends Comparable<Key>, Value> implements ST<Key, Value> {

	private Node root;
	
	private final static boolean RED = true, BLACK = false;
	
	private class Node{
		
		Node right;
		
		Node left;
		
		boolean color;
		
		int N;
		
		Key key;
		
		Value value;
		
		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			this.N = 1;
			this.color = RED;
		}
	}
	
	private boolean isRed(Node node) {
		return node == null ? false : node.color;
	}
	
	@Override
	public Value get(Key key) {
		Node result = get(root, key);
		return result == null ? null : result.value;
	}
	
	private Node get(Node node, Key key) {
		
		if (node == null) {
			return null;
		}
		
		int cmp = key.compareTo(node.key);
		if (cmp > 0) {
			return get(node.right, key);
		} else if (cmp < 0) {
			return get(node.left, key);
		}
		
		return node;
	}

	@Override
	public void put(Key key, Value value) {
		
		root = put(root, key, value);
		root.color = BLACK;
	}
	
	private Node put(Node node, Key key, Value value) {
		
		if (node == null) {
			return new Node(key, value);
		}
		
		int cmp = key.compareTo(node.key);
		
		if (cmp > 0) {
			node.right = put(node.right, key, value);
		} else if (cmp < 0) {
			node.left = put(node.left, key, value);
		} else {
			node.value = value;
		}
		/**
		 * 在插入的多种情况中, 无非是需要将当前节点左旋,  左旋后可能为一个 3-节点, 或两个3-节点, 在后者的情况下, 就需要
		 * 右旋处理, 在这之后, 就需要变色. 因此按照这样的顺序, 已经考虑到了所有的情况, 简洁明快.
		 * 
		 * 一个红链接所链接的两个节点构成了一个3-节点.
		 * 
		 * 同时这段代码在递归之后, 表示向上遍历, 如果放在递归之前就可以表示向下遍历.
		 */
		if (isRed(node.right) && !isRed(node.left)) {
			//在一个2-节点中插入一个新节点, 将父节点变为 3-节点即可
			node = rotateLeft(node);
		}
		
		if (isRed(node.left) && node.left != null && isRed(node.left.left)) {
			//在一个父节点为3-节点中插入一个新节点, 将当前节点变为 4-节点. 逐层向上拆解.
			node = rotateRight(node);
		}
		
		if (isRed(node.right) && isRed(node.left)) {
			//拆解一个 4-节点, 将其变为 父节点为 3-节点, 子节点为两个2-节点即可.
			//或是 父节点为3-节点, 将其变为4-节点, 然后递归逐层拆解即可.
			flipColor(node);
		} 
		
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	private int size(Node node) {
		return node == null ? 0 : node.N;
	}
	
	private Node rotateLeft(Node node) {
		Node rNode = node.right;
		node.right = rNode.left;
		rNode.color = node.color;
		node.color = RED;
		rNode.N = node.N;
		node.N = size(node.left) + size(node.right) + 1;
		rNode.left = node;
		return rNode;
	}
	
	private Node rotateRight(Node node) {
		Node rNode = node.left;
		node.left = rNode.right;
		rNode.color = node.color;
		node.color = RED;
		rNode.N = node.N;
		node.N = size(node.left) + size(node.right) + 1;
		rNode.right = node;
		return rNode;
	}
	
	private void flipColor(Node node) {
		node.right.color = node.left.color = BLACK;
		node.color = RED;
	}

	/**
	 * 在深入的了解了deleteMax 和 deleteMin 这两个方法之后: 总结如下:
	 * 1:在左节点为红时右旋, 右节点为红时左旋, 均不会影响树的高度, 无论另一个节点构成及颜色.
	 * 2:右旋时, 需要关注 左子节点的 左子节点, 左左节点的颜色会成为 左节点的颜色, 右树的颜色会向下推移一个高度.
	 * 3:左旋时, 需要关注 右子节点的 左子节点, 右左节点的颜色会成为 左右节点的颜色, 右右节点的颜色会成为右节点的颜色, 左树颜色会下推移一个高度.
	 * 4:flipColor与colorFlip方法恰好相反, 分解4-节点, 形成4-节点, 无论哪种都不会对树高产生影响.
	 * 5:不仅仅需要关注向下变换, 同时也要关注向上回溯. 右右均为红, 回溯会失败.
	 * 6:需要关注, 当 deleteMin(node)操作, 当前根节点不再是root节点时, 颜色变换需要小心翼翼.
	 * 7:在子树中做删除操作时, 不能让树的高度产生变化是 delete方法的核心.
	 * 在不断地反思, 摸索中才对树的理解更深. 才明白算法之精巧.
	 * 左旋右旋变色操作, 实在是核心到不能再核心的操作.
	 */
	@Override
	public void delete(Key key) {
		/**
		 * 同样的, 在调用 moveRedLeft() 和 moveRedRight() 时, 需要保证node节点必须是红色.
		 */
		if (root == null || key == null) {
			return;
		}
		
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = delete(root, key);
		
		if (isEmpty()) {
			root.color = BLACK;
		}
	}
	
	/**
	 * 在删除操作中, 每一步都设计的很精巧. 需要多多揣摩, 没有任何一步是冗余设计.
	 */
	private Node delete(Node node, Key key) {
		
		int cmp = key.compareTo(node.key);
		
		if (cmp < 0) {
			/**
			 * 如果小于则将当前节点变为红色节点, 或从右节点借来一个节点, 无论哪种情况都不会影响继续向左子树深入.
			 * 从右节点借来的必然替换当前节点, 且大于所有的左子节点.
			 */
			if (!isRed(node.left) && node.left != null && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = delete(node.left, key);
		} else {
			/**
			 * 如果大于等于, 且节点为红色, 右旋后必定不会等于 0.
			 */
			if (isRed(node.left)) {
				node = rotateRight(node);
			}
			/**
			 * 如果右旋后, 结果必定为false, 仅仅是将 cmp 重新赋值而已.
			 * 如果当前节点为要被删除节点, 同时左节点为红色, 会导致当前节点下沉至右子树;
			 * 如果不是被删除节点, 同样需要向右深入.
			 * 
			 * 如果本节点为被删除节点, 同时右节点为null, 且左节点为黑色, 则本节点必然为叶节点.
			 * 
			 * 还少一种情况, 本节点为被删除节点, 且左节点为黑色不为空节点.需要将本节点变为红色.然后做进一步处理.
			 */
			if (key.compareTo(node.key) == 0 && node.right == null) {
				return null;
			}
			
			/**
			 * 在这里就不难发现, 就是在deleteMax中的, 转换, 将当前节点转换为3-节点:
			 * 1:需要且可以转换, 则会导致两种情况, 当前节点下沉一个节点, 左节点上浮; 当前节点不变, 只改变颜色. 
			 * 在这里只考虑如果当前节点恰好是被删除节点(因为比较复杂), 如果不是被删除节点, 常规操作, 不多解释.
			 * 1.1会不断下沉, 直到1.2情况出现, 或第二种情况出现. 
			 * 1.2: 只改变颜色, 意味着, 当前节点, 左右节点构成了一个4-节点, 可以进行删除操作.
			 * 2:如果颜色不转换, 对应的:
			 * 如果当前节点为被删除节点:  左节点为黑色, 右节点不为空, 且右左节点为黑色.
			 * 直接进行下一个 if操作即可, 删除节点.
			 * 
			 * 不难发现:在下面的删除节点操作中, 当前节点是否为3-/4-节点都不影响删除操作, 因为这里的删除操作事实上
			 * 是将删除当前节点转换为deleteMin(node.right) 实现的. 而非删除当前节点, 不会影响树平衡性.
			 * 那为什么要多此一举?做出这一步操作?
			 * 在下面进行解释:
			 */
			if (!isRed(node.right) && node.right != null && !isRed(node.right.left)) {
				node = moveRedRight(node);
			}
			
			if (key.compareTo(node.key) == 0) {
				Node temp = min(node.right);
				node.value = temp.value;
				node.key = temp.key;
				/**
				 * 在这里解释当前节点为被删除节点的第二种情况.左节点为黑色, 右节点不为空, 且右左节点为黑色.
				 * 
				 * 首先:
				 * 在这种删除操作中, 需要保证的一点是, 保证树的高度不变. 在正常的 deleteMin方法中, 事实上是允许
				 * 根节点的高度-1这种情况存在的, 但在这里并不允许这种情况出现:
				 * 假设根节点 也就是 node.right为根节点的树, 高度 -1, 几种可能:
				 * 1:左节点为黑色节点, 进行删除的时候, 右节点变红, 同时左旋, 高度减一. deleteMin(node)进行了一个 colorFlip()操作,
				 * 此时如果说 node.right本来为黑色, 进行操作之后, 高度减一, 因此才需要上面的 if判断语句, 将node.right颜色置为红色,
				 * 在 colorFlip操作中, 使得高度不变.
				 * 
				 * 2:左节点为红色节点 此时左左节点也为红色节点, 通过右旋操作, 将右树变红, flipColor变色,根节点颜色由 黑-红, 即完成了一次转换. 
				 * 需要注意到当这种情况时, 上面的if内容并没有执行, 因此 node.right依然为黑色节点. 在变色之后, 变为红色节点.
				 * 完美的保持了此次转换高度不变.
				 * 
				 * 为什么要防止高度减一, 则是因为node.right节点未必是根节点, 在后续的对balance()操作中, 并没有办法对 以
				 * node.right为根节点的树 高度减一的变化产生影响, 导致左右树高度不平衡.
				 */
				node.right = deleteMin(node.right);
			} else {
				node.right = delete(node.right, key);
			}
		}
		return balance(node);
	}

	@Override
	public boolean contains(Key key) {
		
		return contains(root, key);
	}
	
	private boolean contains(Node node, Key key) {
		
		if (node == null) {
			return false;
		}
		int cmp = key.compareTo(node.key);
		
		if (cmp > 0) {
			return contains(node.right, key);
		} else if (cmp < 0) {
			return contains(node.left, key);
		} else {
			return true;
		}
	}

	@Override
	public boolean isEmpty() {
		
		return root == null;
	}

	@Override
	public int size() {
		
		return size(root);
	}

	@Override
	public int rank(Key min, Key max) {
		if (isEmpty()) {
			return 0;
		} else {
			return rank(root, min, max);
		}
	}
	
	private int rank(Node node, Key min, Key max) {
		
		int count = 0;
		
		if (min.compareTo(node.key) < 0 && max.compareTo(node.key) > 0) {
			count = count + rank(node.left, min, max) + rank(node.right, min, max) + 1;
		} else {
			return 0;
		}
		
		return count;
		
	}

	@Override
	public Key select(int k) {
		
		Node node;
		if (isEmpty()) {
			node = null;
		} else {
			node = select(root, size(root.left) + 1, k);
		}
		
		return node == null ? null : node.key;
	}
	
	private Node select(Node node, int current, int k) {
		
		if ( node.N < k) {
			return null;
		}
		
		if (current == k) {
			return node;
		} else if (current < k) {
			return select(node.right, current + 1, k);
		} else {
			return select(node.left, current - 1, k);
		}
		
		
		
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
		
		if (node.right == null) {
			return node;
		} else {
			return max(node.right);
		}
	}

	@Override
	public Key min() {
		return root == null ? null : min(root).key;
	}
	
	private Node min(Node node) {
		
		if (node.left == null) {
			return node;
		}
		
		return min(node);
	}

	@Override
	public Key floor(Key key) {
		Node r = floor(root, key);
		return r == null ? null : r.key;
	}
	
	private Node floor(Node node, Key key) {
		
		if (node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		
		if (cmp == 0) {
			return node;
		} else if (cmp < 0) {
			return floor(node.left, key);
		}
		
		Node temp = floor(node.right, key);
		return temp == null ? node : temp;
		
	}

	@Override
	public Key ceiling(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 与删除最小节点类似, 就不再多做解释.
	 */
	@Override
	public void deleteMax() {
		
		if (root != null && !isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMax(root);
		if (isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node deleteMax(Node node) {
		/**
		 * 有所不同, 在要删除最大节点时, 如果此时存在左子节点, 但右节点为空, 应该删除当前节点, 如果通过
		 * node.right == null 直接判断的话, 会删除当前节点及其左节点. 所以需要先进行右旋操作.这里的右旋操作
		 * 其目的并不是为了借一个节点, 而是为了防止上述情况出现.
		 * 
		 * 在deleteMin中解释了如何保证当前节点必然为红色, 而在这里则是凭借这段代码保证了当前节点必然为红色.有兴趣的可以
		 * 画图想一下.
		 * 
		 * 所以其实也不难明白, 我们在deleteMax() 无参数的方法中,主要保证第一次调用的时候 node为红色/node的左节点为红色即可.
		 */
		if (isRed(node.left)) {
			node = rotateRight(node);
		}
		if (node.right == null) {
			return null;
		}
		
		/**
		 * 当右节点为2-节点的时候, 和deleteMin()的处理策略一样. 同时 !isRed(node.right) 也使得刚刚旋转过的节点可以继续深入.
		 */
		if (!isRed(node.right) && !isRed(node.right.left)) {
			node = moveRedRight(node);
		}
		
		return balance(node);
	}
	
	private Node moveRedRight(Node node) {
		
		colorFlip(node);
		
		/**
		 * 在这一步则始终不理解为什么,  如果亲兄弟节点为 2-节点, 则右旋, 否则, 不变. 不符合之前的理论, 如果亲兄弟节点不为 2-节点, 则
		 * 借一个节点过来.
		 * 
		 * 这里的处理方式需要特别注意, 如果自己尝试画图就能够明白, 当在删除最小节点的时候, 并没有这样的要求,在这里我用-表示左链接, +表示右链接,
		 * 用r表示当前根节点,~ 表示链接, 同时仅标注红色节点.
		 * 在删除最小节点的最坏情况是 a- ~ b- ~ c(r) ~ d+ , 不难看出为两个红色的左链接, 在向回追溯的时候,按照我们的处理方式一定能够处理掉.
		 * 
		 * 但在这里,情况有所不同, 让我们试试在 左左节点为红色节点的时候变换会发生什么:
		 * a- ~ b(r) ~ c+ ~ d+, 问题出现了, 出现了两个连续的 红色右节点, 再按照balance的方式向上回溯, 第一次回溯
		 * a- ~ b(r) ~ d+ ~ c-, 顺着节点继续向上回溯:
		 * a- ~ b- ~ c+ ~ d(r), 问题出现了, 此时我们的节点在向上回溯的过程中已经到达了 d节点, 但其左节点b的右节点c仍然为红色, 但又很
		 * 无奈的发现没有办法进行向下深入, 因为处在向上回溯的过程中. 错误就出现了.
		 * 
		 * 因此综上必须在 左左节点为黑色的情况下才能进行转换.
		 */
		if (!isRed(node.left.left)) {
			node = rotateRight(node);
		}
		
		return node;
	}

	@Override
	public void deleteMin() {
		if (root != null && !isRed(root.left)) {
			/**
			 * 保证在 moveRedLeft时, 其父节点必须为红色节点.
			 */
			root.color = RED;
		}
		root = deleteMin(root);
		if (! isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node deleteMin(Node node) {
		
		/**
		 * 通过这句代码就直接将最小节点删除了.
		 */
		if (node.left == null) {
			return null;
		}
		
		/**
		 * 如果按照 2-3树的方法, 找到要删除的最小节点, 需要保证被删除的节点为一个 3-节点, 从中删除最小键即可.
		 * 但要保证被删除的节点为3-节点, 在当前节点非3-节点的时候, 需要从兄弟节点借一个节点过来, 或从父级节点借一个节点.
		 * 这就出现问题了, 如果兄弟节点非3-节点. 且因为是平衡二叉树, 兄弟节点必然不存在子节点, 于是需要从父级节点借一个节点来
		 * 但同样的问题又出现在父级节点, 除非父级节点为3-或4-节点, 才能够借来节点.
		 * 
		 * 因此就需要在向下遍历的过程中, 保证左节点始终不是2-节点.
		 * 
		 * 同时也不难发现, 在向下遍历的过程中, 唯有遇到两个连续的黑色节点时才会停留下来进行转换, 而此时当前节点必然为红色
		 * 这就很好的满足了 moveRedLeft的条件,当前节点必定为红色.
		 */
		if (!isRed(node.left) && !isRed(node.left.left)) {
			/**
			 *要验证左子节点为2-节点, 除了要验证 左子节点本身不为红, 同时需验证左子节点的左子节点也不为红才行.
			 *此时直接使用 moveRedLeft即可
			 */
			node = moveRedLeft(node);
		}
		/**
		 * 到这里为止, 就是向下不断深入这棵树, 将所有的左子节点均变为非 2-节点.
		 */
		node.left = deleteMin(node.left);
		
		/**
		 * 这一步的目的, 需要自底向上, 分解所有的4-节点, 还原所有的不合理的 红色右节点.
		 * 删除最底层节点的最小值.
		 */
		return balance(node);
	}
	
	private Node balance(Node node) {
		/**
		 * 在这里需要考虑当前的树是一种怎样的情形, 就需要看如何深入的, 如果左子节点为红色节点,  或左节点的左子节点为红色节点, 则直接深入,则不难
		 * 发现, 每次进行 moveRedLeft变换的节点, 当前节点必然为红色节点, 变换之后, 当前节点为4-节点, 左右节点均为红色, 同时跳过下个节点.
		 * 这样就会有以下几种情况, 左右节点均为红色同时当前节点为黑色. 左节点红色, 连续两个左节点均为红色.且右节点红色.
		 * 
		 * 那么下面这句代码只会在最末级节点, 即左右节点均为红色的同时, 左节点被删除, 因此需要进行左旋转且无法变色.
		 * 如果当前左右均为红色节点, 则会导致连续两个红色左节点出现.
		 */
		if (isRed(node.right)) {
			node = rotateLeft(node);
		}
		
		if (isRed(node.left) && node.left != null && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if (isRed(node.right) && isRed(node.left)) {
			flipColor(node);
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * 这个方法并不是自己想出来的, 仅仅能一点点解析, 并理解
	 * @param node
	 * @return
	 */
	private Node moveRedLeft(Node node) {
		/**
		 * 在这里, 无论是在任何时候, 操作一棵二叉树的时候, 最关键的一点, 维持树的高度不变, 或是在根节点
		 * root 节点进行增高操作. flipColors 就很好的维持了树高不变, 当node节点为 红节点, 子节点
		 * 左树不为红, 右树不可能为红节点的情况下, 将自身节点转为黑色, 两个子节点颜色变红, 维持了树高不变.
		 * 
		 * 至于原因, 在我看来, 是当对两个子节点都为黑色节点的 父节点进行操作的时候, 会使得变化后的左节点变为红色
		 * 节点, 令左侧树高减一. 因此需要在保证左节点已经为红色节点的时候, 进行旋转操作. 至于保证左节点为红, 就是
		 * 采取 flipColors操作即可.
		 */
		colorFlip(node);
		
		/**
		 * 这句代码, 如果右节点为 2-节点, 直接返回, 在总的变化来看, 便是将左, 右子节点,以及父节点中的最小节点,
		 * 三者合并为一个 4-节点.同样的关键点在于 flipColors.
		 * 
		 * 而在这个方法中有一个很关键的点, 则是保证 node节点为红色节点. 如果node节点本身为黑色节点, 则无法保证在变换后
		 * 树的高度维持不变.
		 * 
		 * 如果右节点为3-节点. 从右节点中借来一个节点, 使得左节点变为 3-节点. 而令当前节点变为了4-节点. 换句话说是使得当前节点变为
		 * 5-节点, 保持树高不变.
		 */
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
		}
		return node;
	}
	
	
	private void colorFlip(Node n) {
		/**
		 * 与colorFlip相对应, 生成一个4-节点.
		 */
		n.color = BLACK;
		n.left.color = n.right.color = RED;
	}

	@Override
	public boolean isBinaryTree() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOrdered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNoDuplicates() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printLevel() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
