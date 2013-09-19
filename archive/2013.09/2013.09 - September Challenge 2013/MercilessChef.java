package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MercilessChef {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] baseHealth = new int[count];
		int[] parent = new int[count];
		IOUtils.readIntArrays(in, baseHealth, parent);
		final int[] health = new int[count + 1];
		DFSOrder order = new DFSOrder(Graph.createTree(parent));
		for (int i = 0; i < count; i++)
			health[order.position[i + 1]] = baseHealth[i];
		IntervalTree tree = new IntervalTree(count + 1, false) {
			long[] minHealth;
			long[] remainingChiefs;
			long[] deltaHealth;

			@Override
			protected void initData(int size, int nodeCount) {
				minHealth = new long[nodeCount];
				remainingChiefs = new long[nodeCount];
				deltaHealth = new long[nodeCount];
			}

			@Override
			protected void initAfter(int root, int left, int right, int middle) {
				minHealth[root] = Math.min(minHealth[2 * root + 1], minHealth[2 * root + 2]);
				remainingChiefs[root] = remainingChiefs[2 * root + 1] + remainingChiefs[2 * root + 2];
			}

			@Override
			protected void initBefore(int root, int left, int right, int middle) {
			}

			@Override
			protected void initLeaf(int root, int index) {
				minHealth[root] = health[index];
				remainingChiefs[root] = 1;
			}

			@Override
			protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
				pushUp(root);
			}

			private void pushUp(int root) {
				minHealth[root] = Math.min(minHealth[2 * root + 1], minHealth[2 * root + 2]) + deltaHealth[root];
				remainingChiefs[root] = remainingChiefs[2 * root + 1] + remainingChiefs[2 * root + 2];
			}

			@Override
			protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
				pushDown(root);
			}

			private void pushDown(int root) {
				minHealth[2 * root + 1] += deltaHealth[root];
				minHealth[2 * root + 2] += deltaHealth[root];
				deltaHealth[2 * root + 1] += deltaHealth[root];
				deltaHealth[2 * root + 2] += deltaHealth[root];
				deltaHealth[root] = 0;
			}

			@Override
			protected void updateFull(int root, int left, int right, int from, int to, long delta) {
				minHealth[root] += delta;
				deltaHealth[root] += delta;
				while (minHealth[root] <= 0)
					removeDead(root, left, right);
			}

			private void removeDead(int root, int left, int right) {
				if (left == right) {
					minHealth[root] = Integer.MAX_VALUE / 2;
					remainingChiefs[root] = 0;
				} else {
					pushDown(root);
					if (minHealth[2 * root + 1] <= 0)
						removeDead(2 * root + 1, left, (left + right) >> 1);
					else
						removeDead(2 * root + 2, ((left + right) >> 1) + 1, right);
					pushUp(root);
				}
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
				return remainingChiefs[root];
			}

			@Override
			protected long emptySegmentResult() {
				return 0;
			}
		};
		tree.init();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int chief = in.readInt();
				int decrease = in.readInt();
				tree.update(order.position[chief] + 1, order.end[chief], -decrease);
			} else {
				int chief = in.readInt();
				out.printLine(tree.query(order.position[chief] + 1, order.end[chief]));
			}
		}
    }
}
