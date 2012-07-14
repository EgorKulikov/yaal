package net.egork;

import java.util.Arrays;

public class ThreePoints {
	public long countColoring(int N, int xzero, int xmul, int xadd, int xmod, int yzero, int ymul, int yadd, int ymod) {
		long time = System.currentTimeMillis();
		int[] x = generate(N, xzero, xmod, xadd, xmul);
		int[] y = generate(N, yzero, ymod, yadd, ymul);
		compress(x);
		compress(y);
		IntervalTree tree = new IntervalTree(N);
		int[] order = new int[N];
		for (int i = 0; i < N; i++)
			order[N - 1 - y[i]] = i;
		long answer = 0;
		for (int i : order) {
			answer += tree.query(x[i], N);
			tree.update(0, x[i]);
			tree.activate(x[i]);
		}
		return answer;
	}

	private void compress(int[] array) {
		int[] sorted = array.clone();
		Arrays.sort(sorted);
		for (int i = 0; i < array.length; i++)
			array[i] = Arrays.binarySearch(sorted, array[i]);
	}

	private int[] generate(int count, int zero, int mod, long add, long mul) {
		int[] result = new int[count];
		result[0] = zero;
		for (int i = 1; i < count; i++)
			result[i] = (int) ((result[i - 1] * mul + add) % mod);
		return result;
	}


}

class IntervalTree {
	protected int size;
	protected int[] nodes;
	protected int[] active;
	protected long[] sum;

	protected IntervalTree(int size) {
		this.size = size;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		nodes = new int[nodeCount];
		sum = new long[nodeCount];
		active = new int[nodeCount];
	}

	public void update(int from, int to) {
		update(0, 0, size - 1, from, to);
	}

	private void update(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return;
		if (left >= from && right <= to) {
			nodes[root]++;
			sum[root] += active[root];
			return;
		}
		int middle = (left + right) >> 1;
		accumulate(2 * root + 1, root, middle - left + 1);
		accumulate(2 * root + 2, root, right - middle);
		nodes[root] = 0;
		update(2 * root + 1, left, middle, from, to);
		update(2 * root + 2, middle + 1, right, from, to);
		sum[root] = sum[2 * root + 1] + sum[2 * root + 2];
	}

	private void accumulate(int child, int root, long childLength) {
		nodes[child] += nodes[root];
		sum[child] += (long)active[child] * nodes[root];
	}

	public long query(int from, int to) {
		return query(0, 0, size - 1, from, to);
	}

	private long query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return 0;
		if (left >= from && right <= to)
			return sum[root];
		int middle = (left + right) >> 1;
		accumulate(2 * root + 1, root, middle - left + 1);
		accumulate(2 * root + 2, root, right - middle);
		nodes[root] = 0;
		return  query(2 * root + 1, left, middle, from, to) + query(2 * root + 2, middle + 1, right, from, to);
	}

	public void activate(int position) {
		activate(0, 0, size - 1, position);
	}

	private void activate(int root, int left, int right, int position) {
		if (left > position || right < position)
			return;
		if (left != right) {
			int middle = (left + right) >> 1;
			accumulate(2 * root + 1, root, middle - left + 1);
			accumulate(2 * root + 2, root, right - middle);
			nodes[root] = 0;
			activate(2 * root + 1, left, middle, position);
			activate(2 * root + 2, middle + 1, right, position);
		}
		active[root]++;
	}
}
