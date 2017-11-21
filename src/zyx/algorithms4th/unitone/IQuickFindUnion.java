package zyx.algorithms4th.unitone;

//定义 1.5章 API
public interface IQuickFindUnion {

	/*个人思考, 在现实生活中 类似的例子有, 基站, 网络连接, 等其他方式, 明显是可以实现 delete方法的, 原因对比如下:
	 * 在 基站体系中, 层级较为固定, 有最高层级限制, 对每一个基站 可以有多个下属节点, 节点层次划分比较清楚明白, 不存在
	 * 树的高度可以无限高的问题, 且对于用户层次, 无需考虑不同层次子节点 之间的连通性, 也就是说 连接同一  wifi 的电脑, 如果再开wifi,
	 * 下面又有子节点, 不属于同一层级, 则不考虑连通性问题. 问题被简化, 可操作空间也就被扩大了.
	 * */
	
	//找到给出数字所属的分量
	int find(int p);
	
	//判断两个数字是否属于同一个分量
	boolean connected(int p, int q);
	
	//将两个数字 连通
	void union(int p, int q);
	
	//统计当前分量数
	int count();
}
