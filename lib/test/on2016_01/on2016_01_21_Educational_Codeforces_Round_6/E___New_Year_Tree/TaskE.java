package on2016_01.on2016_01_21_Educational_Codeforces_Round_6.E___New_Year_Tree;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] color = IOUtils.readIntArray(in, n);
		int[] x = new int[n - 1];
		int[] y = new int[n - 1];
		IOUtils.readIntArrays(in, x, y);
		MiscUtils.decreaseByOne(x, y, color);
		BidirectionalGraph graph = BidirectionalGraph.createGraph(n, x, y);
		DFSOrder order = new DFSOrder(graph);
		LongIntervalTree tree = new LongIntervalTree(n) {
			@Override
			protected long joinValue(long left, long right) {
				return left | right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return delta == -1 ? was : delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return delta == -1 ? value : delta;
			}

			@Override
			protected long neutralValue() {
				return 0;
			}

			@Override
			protected long neutralDelta() {
				return -1;
			}
		};
		for (int i = 0; i < n; i++) {
			tree.update(order.position[i], order.position[i], 1L << color[i]);
		}
		for (int i = 0; i < m; i++) {
			int t = in.readInt();
			int v = in.readInt() - 1;
			if (t == 1) {
				int c = in.readInt() - 1;
				tree.update(order.position[v], order.end[v], 1L << c);
			} else {
				out.printLine(Long.bitCount(tree.query(order.position[v], order.end[v])));
			}
		}
	}
}
