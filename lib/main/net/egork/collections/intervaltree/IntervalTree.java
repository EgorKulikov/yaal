package net.egork.collections.intervaltree;

/**
 * @author egor@egork.net
 */
public abstract class IntervalTree<V> {
	protected int size;
	protected V[] nodes;

	protected IntervalTree(int size) {
		this.size = size;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		//noinspection unchecked
		nodes = (V[])new Object[nodeCount];
	}

	protected abstract V join(V left, V right);
	protected abstract V accumulate(V destination, V source, int destinationLength);
	protected abstract V neutral();

	protected V initNode(int index) {
		return neutral();
	}

	public void init() {
		init(0, 0, size - 1);
	}

	private void init(int root, int left, int right) {
		if (left == right)
			nodes[root] = initNode(left);
		else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle);
			init(2 * root + 2, middle + 1, right);
			nodes[root] = join(nodes[2 * root + 1], nodes[2 * root + 2]);
		}
	}

	public void update(int from, int to, V value) {
		update(0, 0, size - 1, from, to, value);
	}

	private void update(int root, int left, int right, int from, int to, V value) {
		if (left > to || right < from)
			return;
		if (left >= from && right <= to) {
			nodes[root] = accumulate(nodes[root], value, right - left + 1);
			return;
		}
		int middle = (left + right) >> 1;
		nodes[2 * root + 1] = accumulate(nodes[2 * root + 1], nodes[root], middle - left + 1);
		nodes[2 * root + 2] = accumulate(nodes[2 * root + 2], nodes[root], right - middle);
		update(2 * root + 1, left, middle, from, to, value);
		update(2 * root + 2, middle + 1, right, from, to, value);
		nodes[root] = join(nodes[2 * root + 1], nodes[2 * root + 2]);
	}

	public V query(int from, int to) {
		return query(0, 0, size - 1, from, to);
	}

	private V query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return neutral();
		if (left >= from && right <= to)
			return nodes[root];
		int middle = (left + right) >> 1;
		nodes[2 * root + 1] = accumulate(nodes[2 * root + 1], nodes[root], middle - left + 1);
		nodes[2 * root + 2] = accumulate(nodes[2 * root + 2], nodes[root], right - middle);
		V leftResult = query(2 * root + 1, left, middle, from, to);
		V rightResult = query(2 * root + 2, middle + 1, right, from, to);
		nodes[root] = join(nodes[2 * root + 1], nodes[2 * root + 2]);
		return join(leftResult, rightResult);
	}
}
