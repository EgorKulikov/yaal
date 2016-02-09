package on2016_01.on2016_01_16_SNWS_2016__R2.A___Alchemy;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		Graph graph = new Graph(n);
		for (int i = 1; i < n; i++) {
			graph.addSimpleEdge(in.readInt() - 1, i);
		}
		DFSOrder order = new DFSOrder(graph);
		int qty = 0;
		for (int i = 0; i < n; i++) {
			if (graph.firstOutbound(i) == -1) {
				qty++;
			}
		}
		int[] queue = new int[qty];
		for (int i = 0; i < m; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			int was = queue[a];
			if (order.position[was] > order.end[b] || order.position[b] > order.end[was]) {
				out.printLine(0);
				return;
			}
			if (order.position[b] > order.position[was]) {
				queue[a] = b;
			}
		}
		long answer = 1;
		SumIntervalTree tree = new SumIntervalTree(n);
		for (int i = 0; i < n; i++) {
			if (graph.firstOutbound(i) == -1) {
				tree.update(order.position[i], order.position[i], 1);
			}
		}
		int[] pOrder = ArrayUtils.order(queue);
		ArrayUtils.reverse(pOrder);
		for (int i : pOrder) {
			long ways = tree.query(order.position[queue[i]], order.end[queue[i]]);
			answer *= ways;
			answer %= MOD;
			tree.update(order.position[queue[i]], order.position[queue[i]], -1);
		}
		out.printLine(answer);
	}
}
