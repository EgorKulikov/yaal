package net.egork.collections.intervaltree;

import net.egork.misc.PowerOperation;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
@SuppressWarnings({"unchecked"})
public class IntervalTree<V> {
	protected int[] left;
	protected int[] right;
	protected Object[] value;
	protected Object[] delta;
	private final PowerOperation<V> operation;

	public IntervalTree(int size, PowerOperation<V> operation) {
		this.operation = operation;
		int arraysSize = Math.max(1, Integer.highestOneBit(size) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new Object[arraysSize];
		delta = new Object[arraysSize];
		initTree(0, size, 0);
	}

	private void initTree(int left, int right, int root) {
		this.left[root] = left;
		this.right[root] = right;
		this.value[root] = operation.create();
		this.delta[root] = operation.create();
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1);
			initTree((left + right + 1) / 2, right, 2 * root + 2);
		}
	}

	public void putSegment(int left, int right, V value) {
		putSegment(left, right, value, 0);
	}

	private void putSegment(int left, int right, V value, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return;
		this.value[root] = operation.operation((V) this.value[root],
			operation.power(value, intersection(left, right, root)));
		if (left <= this.left[root] && right >= this.right[root]) {
			this.delta[root] = operation.operation((V) this.delta[root], value);
			return;
		}
		putSegment(left, right, value, 2 * root + 1);
		putSegment(left, right, value, 2 * root + 2);
	}

	private int intersection(int left, int right, int root) {
		return Math.min(right, this.right[root]) - Math.max(left, this.left[root]);
	}

	public void put(int position, V value) {
		put(position, value, 0);
	}

	private void put(int position, V value, int root) {
		if (left[root] > position || right[root] <= position)
			return;
		this.value[root] = operation.operation((V) this.value[root], value);
		if (right[root] - left[root] > 1) {
			put(position, value, 2 * root + 1);
			put(position, value, 2 * root + 2);
		} else
			this.delta[root] = operation.operation((V) this.delta[root], value);
	}

	public V get(int position) {
		return get(position, 0);
	}

	private V get(int position, int root) {
		if (position >= right[root] || position < left[root])
			return operation.create();
		if (right[root] - left[root] == 1)
			return (V) value[root];
		return operation.operation(operation.operation(get(position, 2 * root + 1), get(position, 2 * root + 2)),
			(V) delta[root]);
	}

	public V getSegment(int left, int right) {
		return getSegment(left, right, 0);
	}

	private V getSegment(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return operation.create();
		if (left <= this.left[root] && right >= this.right[root])
			return (V) value[root];
		return operation.operation(operation.operation(getSegment(left, right, 2 * root + 1),
			getSegment(left, right, 2 * root + 2)),
			operation.power((V) delta[root], intersection(left, right, root)));
	}
}
