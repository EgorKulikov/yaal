package on2014_02.on2014_02_26_Codeforces_Round__232__Div__1_.C___On_Changing_Tree;



import net.egork.collections.intervaltree.LCA;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count - 1);
		MiscUtils.decreaseByOne(parent);
		Graph graph = new Graph(count, count - 1);
		for (int i = 0; i < count - 1; i++)
			graph.addSimpleEdge(parent[i], i + 1);
		DFSOrder order = new DFSOrder(graph);
		LCA lca = new LCA(graph);
		SumIntervalTree base = new SumIntervalTree(count);
		SumIntervalTree delta = new SumIntervalTree(count);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int at = in.readInt() - 1;
				long baseValue = in.readInt();
				long deltaValue = in.readInt();
				delta.update(order.position[at], order.end[at], deltaValue);
				baseValue += deltaValue * lca.getLevel(at);
				baseValue %= MOD;
				base.update(order.position[at], order.end[at], baseValue);
			} else {
				int at = in.readInt() - 1;
				long answer = (base.query(order.position[at], order.position[at]) - lca.getLevel(at) * (delta.query(order.position[at], order.position[at]) % MOD)) % MOD;
				if (answer < 0)
					answer += MOD;
				out.printLine(answer);
			}
		}
    }
}
