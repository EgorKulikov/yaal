package on2013_12.on2013_12_13_USACO_2013_December_Contest__Gold.Problem_2__Optimal_Milking;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Optmilk {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int dayCount = in.readInt();
		int[] initial = IOUtils.readIntArray(in, count);
		SpecialIntervalTree tree = new SpecialIntervalTree(initial);
		long answer = 0;
		for (int i = 0; i < dayCount; i++) {
			int at = in.readInt() - 1;
			int value = in.readInt();
			tree.update(at, at, value);
			answer += tree.query();
		}
		out.printLine(answer);
    }

	static class SpecialIntervalTree extends IntervalTree {
		long[][] answer;
		int[] initial;

		SpecialIntervalTree(int[] initial) {
			super(initial.length, false);
			this.initial = initial;
			init();
		}

		long query() {
			return answer[3][0];
		}

		@Override
		protected void initData(int size, int nodeCount) {
			answer = new long[4][nodeCount];
		}

		@Override
		protected void initAfter(int root, int left, int right, int middle) {
			update(root);
		}

		private void update(int root) {
			for (int i = 0; i < 4; i++)
				answer[i][root] = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if ((i & 1) == 1 && (j & 2) == 2)
						continue;
					answer[(i & 2) + (j & 1)][root] = Math.max(answer[(i & 2) + (j & 1)][root],
						answer[i][2 * root + 1] + answer[j][2 * root + 2]);
				}
			}
		}

		@Override
		protected void initBefore(int root, int left, int right, int middle) {

		}

		@Override
		protected void initLeaf(int root, int index) {
			answer[3][root] = initial[index];
		}

		@Override
		protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
			update(root);
		}

		@Override
		protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {

		}

		@Override
		protected void updateFull(int root, int left, int right, int from, int to, long delta) {
			answer[3][root] = delta;
		}

		@Override
		protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
			return 0;
		}

		@Override
		protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {

		}

		@Override
		protected long queryFull(int root, int left, int right, int from, int to) {
			return 0;
		}

		@Override
		protected long emptySegmentResult() {
			return 0;
		}
	}
}
