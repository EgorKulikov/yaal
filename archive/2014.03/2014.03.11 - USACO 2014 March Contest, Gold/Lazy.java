package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Lazy {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int side = in.readInt();
		int[] amount = new int[count];
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, amount, x, y);
		IntervalTree tree = new IntervalTree(2000001) {
			int[] value;
			int[] delta;

			@Override
			protected void initData(int size, int nodeCount) {
				value = new int[nodeCount];
				delta = new int[nodeCount];
			}

			@Override
			protected void initAfter(int root, int left, int right, int middle) {

			}

			@Override
			protected void initBefore(int root, int left, int right, int middle) {

			}

			@Override
			protected void initLeaf(int root, int index) {

			}

			@Override
			protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
				value[root] = Math.max(value[2 * root + 1], value[2 * root + 2]) + this.delta[root];
			}

			@Override
			protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {

			}

			@Override
			protected void updateFull(int root, int left, int right, int from, int to, long delta) {
				this.delta[root] += delta;
				value[root] += delta;
			}

			@Override
			protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
				return Math.max(leftResult, rightResult) + delta[root];
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
				return 0;
			}
		};
		for (int i = 0; i < count; i++) {
			int nx = x[i] + y[i];
			int ny = x[i] - y[i];
			x[i] = nx;
			y[i] = ny;
		}
		int[] order = ArrayUtils.order(y);
		int j = 0;
		long answer = 0;
		for (int i : order) {
			while (y[order[j]] + 2 * side < y[i]) {
				int from = x[order[j]] - 2 * side;
				int to = x[order[j]];
				tree.update(from, to, -amount[order[j]]);
				j++;
			}
			tree.update(x[i] - 2 * side, x[i], amount[i]);
			answer = Math.max(answer, tree.query(0, 2000000));
		}
		out.printLine(answer);
    }
}
