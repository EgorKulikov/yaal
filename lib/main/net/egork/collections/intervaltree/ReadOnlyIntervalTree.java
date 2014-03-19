package net.egork.collections.intervaltree;

/**
 * @author egor@egork.net
 */
public abstract class ReadOnlyIntervalTree extends IntervalTree {
	protected long[] value;
	protected long[] array;

	protected ReadOnlyIntervalTree(long[] array) {
		super(array.length, false);
		this.array = array;
		init();
	}

	@Override
	protected void initData(int size, int nodeCount) {
		value = new long[nodeCount];
	}

	@Override
	protected void initAfter(int root, int left, int right, int middle) {
		value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
	}

	@Override
	protected void initBefore(int root, int left, int right, int middle) {
	}

	@Override
	protected void initLeaf(int root, int index) {
		value[root] = array[index];
	}

	@Override
	protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void updateFull(int root, int left, int right, int from, int to, long delta) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
		return joinValue(leftResult, rightResult);
	}

	@Override
	protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
	}

	@Override
	protected long queryFull(int root, int left, int right, int from, int to) {
		return value[root];
	}

	@Override
	protected long emptySegmentResult() {
		return neutralValue();
	}

	@Override
	public void update(int from, int to, long delta) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void update(int root, int left, int right, int from, int to, long delta) {
		throw new UnsupportedOperationException();
	}

	protected abstract long neutralValue();
	protected abstract long joinValue(long left, long right);
}
