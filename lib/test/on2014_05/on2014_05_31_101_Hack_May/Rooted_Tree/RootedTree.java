package on2014_05.on2014_05_31_101_Hack_May.Rooted_Tree;



import net.egork.collections.intervaltree.LCA;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RootedTree {
	private static final long MOD = (long) (1e9 + 7);
	long reverse = (MOD + 1) / 2;

	private DFSOrder order;
	private LCA lca;
	private SumIntervalTree base;
	private SumIntervalTree linear;
	private SumIntervalTree squared;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int root = in.readInt() - 1;
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		BidirectionalGraph tree = BidirectionalGraph.createGraph(count, from, to);
		order = new DFSOrder(tree, root);
		lca = new LCA(tree, root);
		base = new SumIntervalTree(count);
		linear = new SumIntervalTree(count);
		squared = new SumIntervalTree(count);
		int[] parent = new int[count];
		dfs(root, -1, parent, tree);
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'Q') {
				int a = in.readInt() - 1;
				int b = in.readInt() - 1;
				int cLCA = lca.getLCA(a, b);
				long result = value(a) + value(b) - value(cLCA) - value(parent[cLCA]);
				result %= MOD;
				if (result < 0)
					result += MOD;
				out.printLine(result);
			} else {
				int node = in.readInt() - 1;
				long v = in.readInt();
				long k = in.readInt();
				long d = lca.getLevel(node);
				int start = order.position[node];
				int end = order.end[node];
				base.update(start, end, 2 * (-v * d % MOD + v + k * (d * (d - 1) / 2 % MOD)) % MOD);
				linear.update(start, end, (2 * v - k * (2 * d - 1)) % MOD);
				squared.update(start, end, k);
			}
		}
    }

	private long value(int node) {
		if (node == -1)
			return 0;
		int level = lca.getLevel(node);
		node = order.position[node];
		long result = (base.query(node, node) + level * linear.query(node, node) % MOD + level * level % MOD * squared.query(node, node) % MOD) % MOD;
		result *= reverse;
		result %= MOD;
		return result;
	}

	private void dfs(int current, int last, int[] parent, BidirectionalGraph graph) {
		parent[current] = last;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				dfs(next, current, parent, graph);
		}
	}

	static class SumIntervalTree extends LongIntervalTree {
		public SumIntervalTree(int size) {
	        super(size);
	    }

	    @Override
	    protected long joinValue(long left, long right) {
	        return (left + right) % MOD;
	    }

	    @Override
	    protected long joinDelta(long was, long delta) {
	        return (was + delta) % MOD;
	    }

	    @Override
	    protected long accumulate(long value, long delta, int length) {
	        return (value + delta * length) % MOD;
	    }

	    @Override
	    protected long neutralValue() {
	        return 0;
	    }

	    @Override
	    protected long neutralDelta() {
	        return 0;
	    }
	}

}
