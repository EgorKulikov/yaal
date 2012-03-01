package net.egork.collections.intervaltree;

/**
 * @author egor@egork.net
 */
public abstract class IntervalTreeMutableNodes<V> {
	protected int size;
	protected V[] nodes;
	protected V[] result;

	protected IntervalTreeMutableNodes(int size) {
		this.size = size;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		//noinspection unchecked
		nodes = (V[])new Object[nodeCount];
		//noinspection unchecked
		result = (V[])new Object[nodeCount];
	}

	protected abstract void join(V destination, V left, V right);
	protected abstract void accumulate(V destination, V source, int destinationLength);
	protected abstract void copy(V destination, V source);
	protected abstract V neutral();
	protected abstract V createNode();

	protected V initNode(int index) {
		return createNode();
	}

	protected void init() {
		init(0, 0, size - 1);
	}

	private void init(int root, int left, int right) {
		result[root] = createNode();
		if (left == right)
			nodes[root] = initNode(left);
		else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle);
			init(2 * root + 2, middle + 1, right);
			nodes[root] = createNode();
			join(nodes[root], nodes[2 * root + 1], nodes[2 * root + 2]);
		}
	}

	public void update(int from, int to, V value) {
		update(0, 0, size - 1, from, to, value);
	}

	private void update(int root, int left, int right, int from, int to, V value) {
		if (left > to || right < from)
			return;
		if (left >= from && right <= to) {
			accumulate(nodes[root], value, right - left + 1);
			return;
		}
		int middle = (left + right) >> 1;
		accumulate(nodes[2 * root + 1], nodes[root], middle - left + 1);
		accumulate(nodes[2 * root + 2], nodes[root], right - middle);
		update(2 * root + 1, left, middle, from, to, value);
		update(2 * root + 2, middle + 1, right, from, to, value);
		join(nodes[root], nodes[2 * root + 1], nodes[2 * root + 2]);
	}

	public V query(int from, int to) {
		query(0, 0, size - 1, from, to);
		return result[0];
	}

	private void query(int root, int left, int right, int from, int to) {
		if (left > to || right < from) {
			copy(result[root], neutral());
			return;
		}
		if (left >= from && right <= to) {
			copy(result[root], nodes[root]);
			return;
		}
		int middle = (left + right) >> 1;
		accumulate(nodes[2 * root + 1], nodes[root], middle - left + 1);
		accumulate(nodes[2 * root + 2], nodes[root], right - middle);
		query(2 * root + 1, left, middle, from, to);
		query(2 * root + 2, middle + 1, right, from, to);
		join(nodes[root], nodes[2 * root + 1], nodes[2 * root + 2]);
		join(result[root], result[2 * root + 1], result[2 * root + 2]);
	}
}
