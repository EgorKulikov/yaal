package on2015_08.on2015_08_30_XV_Open_Cup_Onsite.B___Movie_goer;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	protected long[] value;
	protected long[] delta;

	protected int size;

	final public void IntervalTree(int size, boolean shouldInit) {
		this.size = size;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		initData(size, nodeCount);
		if (shouldInit)
			init();
	}

	final public void init() {
		if (size == 0)
			return;
		init(0, 0, size - 1);
	}

	private void init(int root, int left, int right) {
		if (left == right) {
			initLeaf(root, left);
		} else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle);
			init(2 * root + 2, middle + 1, right);
			initAfter(root, left, right, middle);
		}
	}

	final public void update(int from, int to, long delta) {
		update(0, 0, size - 1, from, to, delta);
	}

	final protected void update(int root, int left, int right, int from, int to, long delta) {
		if (left > to || right < from)
			return;
		if (left >= from && right <= to) {
			updateFull(root, left, right, from, to, delta);
			return;
		}
		int middle = (left + right) >> 1;
		updatePreProcess(root, left, right, from, to, delta, middle);
		update(2 * root + 1, left, middle, from, to, delta);
		update(2 * root + 2, middle + 1, right, from, to, delta);
		updatePostProcess(root, left, right, from, to, delta, middle);
	}

	final public long query(int from, int to) {
		return query(0, 0, size - 1, from, to);
	}

	final protected long query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return emptySegmentResult();
		if (left >= from && right <= to)
			return queryFull(root, left, right, from, to);
		int middle = (left + right) >> 1;
		queryPreProcess(root, left, right, from, to, middle);
		long leftResult = query(2 * root + 1, left, middle, from, to);
		long rightResult = query(2 * root + 2, middle + 1, right, from, to);
		return queryPostProcess(root, left, right, from, to, middle, leftResult, rightResult);
	}

	final protected void LongIntervalTree(int size) {
		LongIntervalTree(size, true);
	}

	final public void LongIntervalTree(int size, boolean shouldInit) {
		IntervalTree(size, shouldInit);
	}


	final protected void initData(int size, int nodeCount) {
		value = new long[nodeCount];
		delta = new long[nodeCount];
	}

	final protected long initValue(int index) {
		return 0;
	}


	final protected void initAfter(int root, int left, int right, int middle) {
		value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
		delta[root] = neutralDelta();
	}

	final protected void initLeaf(int root, int index) {
		value[root] = initValue(index);
		delta[root] = neutralDelta();
	}


	final protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
		value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
	}


	final protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
		pushDown(root, left, middle, right);
	}

	final protected void pushDown(int root, int left, int middle, int right) {
		value[2 * root + 1] = accumulate(value[2 * root + 1], delta[root], middle - left + 1);
		value[2 * root + 2] = accumulate(value[2 * root + 2], delta[root], right - middle);
		delta[2 * root + 1] = joinDelta(delta[2 * root + 1], delta[root]);
		delta[2 * root + 2] = joinDelta(delta[2 * root + 2], delta[root]);
		delta[root] = neutralDelta();
	}


	final protected void updateFull(int root, int left, int right, int from, int to, long delta) {
		value[root] = accumulate(value[root], delta, right - left + 1);
		this.delta[root] = joinDelta(this.delta[root], delta);
	}


	final protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
		return joinValue(leftResult, rightResult);
	}


	final protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
		pushDown(root, left, middle, right);
	}


	final protected long queryFull(int root, int left, int right, int from, int to) {
		return value[root];
	}


	final protected long emptySegmentResult() {
		return neutralValue();
	}

	final protected long joinValue(long left, long right) {
		return Math.max(left, right);
	}

	final protected long joinDelta(long was, long delta) {
		return was + delta;
	}

	final protected long accumulate(long value, long delta, int length) {
		return value + delta;
	}

	final protected long neutralValue() {
		return Long.MIN_VALUE / 2;
	}

	final protected long neutralDelta() {
		return 0;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] f = IOUtils.readIntArray(in, n);
		int[] w = IOUtils.readIntArray(in, m);
		int[] next = new int[n];
		int[] pos = ArrayUtils.createArray(m, n);
		MiscUtils.decreaseByOne(f);
		for (int i = n - 1; i >= 0; i--) {
			next[i] = pos[f[i]];
			pos[f[i]] = i;
		}
		LongIntervalTree(n);
		long answer = 0;
		for (int i = n - 1; i >= 0; i--) {
			int nxt = next[i];
			int nnxt = nxt == n ? n : next[nxt];
			update(i, nxt - 1, w[f[i]]);
			update(nxt, nnxt - 1, -w[f[i]]);
//			if (next[i] != n) {
//				update(next[i], n - 1, -2 * w[f[i]]);
//				if (next[next[i]] != n) {
//					update(next[next[i]], n - 1, w[f[i]]);
//				}
//			}
			answer = Math.max(answer, query(i, n - 1));
		}
		out.printLine(answer);
	}
}
