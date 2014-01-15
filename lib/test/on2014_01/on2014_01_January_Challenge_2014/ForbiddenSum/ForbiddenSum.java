package on2014_01.on2014_01_January_Challenge_2014.ForbiddenSum;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ForbiddenSum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		SpecialIntervalTree tree = new SpecialIntervalTree(array);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			tree.upTo = 1;
			while (true) {
				int result = (int) (tree.query(from, to) + 1);
				if (result == tree.upTo)
					break;
				tree.upTo = result;
			}
			out.printLine(tree.upTo);
		}
    }

	static class SpecialIntervalTree extends IntervalTree {
		int[] array;
		int[][] values;
		long[][] sums;
		int upTo;

		protected SpecialIntervalTree(int[] array) {
			super(array.length, false);
			this.array = array;
			init();
		}

		@Override
		protected void initData(int size, int nodeCount) {
			values = new int[nodeCount][];
			sums = new long[nodeCount][];
		}

		@Override
		protected void initAfter(int root, int left, int right, int middle) {
			values[root] = Arrays.copyOfRange(array, left, right + 1);
			Arrays.sort(values[root]);
			sums[root] = ArrayUtils.partialSums(values[root]);
			for (int i = 0; i < values[root].length; i++)
				values[root][i] *= 2;
		}

		@Override
		protected void initBefore(int root, int left, int right, int middle) {

		}

		@Override
		protected void initLeaf(int root, int index) {
			initAfter(root, index, index, index);
		}

		@Override
		protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {

		}

		@Override
		protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {

		}

		@Override
		protected void updateFull(int root, int left, int right, int from, int to, long delta) {

		}

		@Override
		protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
			return leftResult + rightResult;
		}

		@Override
		protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {

		}

		@Override
		protected long queryFull(int root, int left, int right, int from, int to) {
			int position = -Arrays.binarySearch(values[root], 2 * upTo + 1) - 1;
			return sums[root][position];
		}

		@Override
		protected long emptySegmentResult() {
			return 0;
		}
	}
}
