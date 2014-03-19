package on2014_03.on2014_03_07_March_Challenge_2014.The_Street;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TheStreet {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] type = new int[queryCount];
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		int[] delta = new int[queryCount];
		int[] base = new int[queryCount];
		for (int i = 0; i < queryCount; i++) {
			type[i] = in.readInt();
			from[i] = in.readInt() - 1;
			if (type[i] == 3)
				continue;
			to[i] = in.readInt() - 1;
			delta[i] = in.readInt();
			base[i] = in.readInt();
		}
		IntList interestingList = new IntArrayList();
		for (int i = 0; i < queryCount; i++) {
			if (type[i] == 3)
				interestingList.add(from[i]);
		}
		interestingList.inPlaceSort();
		int[] interesting = ArrayUtils.unique(interestingList.toArray());
		SpecialIntervalTree tree = new SpecialIntervalTree(interesting);
		for (int i = 0; i < queryCount; i++) {
			int uFrom = Arrays.binarySearch(interesting, from[i]);
			if (uFrom < 0)
				uFrom = -uFrom - 1;
			if (type[i] != 3) {
				int uTo = Arrays.binarySearch(interesting, to[i]);
				if (uTo < 0)
					uTo = -uTo - 2;
				if (uFrom > uTo)
					continue;
				tree.updateBase = base[i] - (long)from[i] * delta[i];
				tree.updateDelta = delta[i];
				tree.update(uFrom, uTo, 2 - type[i]);
			} else {
				long result = tree.query(uFrom, uFrom);
				if (result == Long.MIN_VALUE)
					out.printLine("NA");
				else
					out.printLine(result);
			}
		}
    }

	static class SpecialIntervalTree extends IntervalTree {
		boolean[] set;
		long[] base;
		long[] delta;
		long[] addBase;
		long[] addDelta;
		int[] firstPosition;
		int[] lastPosition;
		int[] positions;

		long updateBase;
		long updateDelta;

		SpecialIntervalTree(int[] positions) {
			super(positions.length, false);
			this.positions = positions;
			init();
		}

		@Override
		protected void initData(int size, int nodeCount) {
			set = new boolean[nodeCount];
			base = new long[nodeCount];
			delta = new long[nodeCount];
			firstPosition = new int[nodeCount];
			lastPosition = new int[nodeCount];
			addBase = new long[nodeCount];
			addDelta = new long[nodeCount];
		}

		@Override
		protected void initAfter(int root, int left, int right, int middle) {
			firstPosition[root] = firstPosition[2 * root + 1];
			lastPosition[root] = lastPosition[2 * root + 2];
			base[root] = Long.MIN_VALUE;
		}

		@Override
		protected void initBefore(int root, int left, int right, int middle) {

		}

		@Override
		protected void initLeaf(int root, int index) {
			firstPosition[root] = positions[index];
			lastPosition[root] = positions[index];
			base[root] = Long.MIN_VALUE;
		}

		@Override
		protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {

		}

		@Override
		protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
			pushDown(root);
		}

		@Override
		protected void updateFull(int root, int left, int right, int from, int to, long delta) {
			if (delta == 0) {
				addBase[root] += updateBase;
				addDelta[root] += updateDelta;
				return;
			}
			update(root, updateBase, updateDelta);
		}

		private void update(int root, long updateBase, long updateDelta) {
			set[root] = true;
			long leftWas = base[root] + firstPosition[root] * this.delta[root];
			long rightWas = base[root] + lastPosition[root] * this.delta[root];
			long leftNew = updateBase + firstPosition[root] * updateDelta;
			long rightNew = updateBase + lastPosition[root] * updateDelta;
			if (leftWas >= leftNew && rightWas >= rightNew)
				return;
			if (leftWas <= leftNew && rightWas <= rightNew) {
				base[root] = updateBase;
				this.delta[root] = updateDelta;
				return;
			}
			pushDown(root);
			update(2 * root + 1, updateBase, updateDelta);
			update(2 * root + 2, updateBase, updateDelta);
		}

		private void pushDown(int root) {
			addBase[2 * root + 1] += addBase[root];
			addBase[2 * root + 2] += addBase[root];
			addDelta[2 * root + 1] += addDelta[root];
			addDelta[2 * root + 2] += addDelta[root];
			addBase[root] = 0;
			addDelta[root] = 0;
			if (base[root] != Long.MIN_VALUE) {
				update(2 * root + 1, base[root], delta[root]);
				update(2 * root + 2, base[root], delta[root]);
				base[root] = Long.MIN_VALUE;
				delta[root] = 0;
			}
		}

		@Override
		protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
			return Math.max(leftResult, rightResult);
		}

		@Override
		protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
			pushDown(root);
		}

		@Override
		protected long queryFull(int root, int left, int right, int from, int to) {
			if (!set[root])
				return Long.MIN_VALUE;
			return base[root] + addBase[root] + (delta[root] + addDelta[root]) * lastPosition[root];
		}

		@Override
		protected long emptySegmentResult() {
			return Long.MIN_VALUE;
		}
	}
}
