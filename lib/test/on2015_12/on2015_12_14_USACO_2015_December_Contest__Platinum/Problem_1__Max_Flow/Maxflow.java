package on2015_12.on2015_12_14_USACO_2015_December_Contest__Platinum.Problem_1__Max_Flow;



import net.egork.collections.intervaltree.LCA;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Maxflow {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] x = new int[n - 1];
		int[] y = new int[n - 1];
		IOUtils.readIntArrays(in, x, y);
		int[] s = new int[k];
		int[] t = new int[k];
		IOUtils.readIntArrays(in, s, t);
		MiscUtils.decreaseByOne(x, y, s, t);
		Graph graph = BidirectionalGraph.createGraph(n, x, y);
		final LCA lca = new LCA(graph);
		int[] add = new int[n];
		int[] remove = new int[n];
		for (int i = 0; i < k; i++) {
			add[s[i]]++;
			add[t[i]]++;
			remove[lca.getLCA(s[i], t[i])]++;
		}
		int[] flow = new int[n];
		int[] order = ArrayUtils.createOrder(n);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				return lca.getLevel(second) - lca.getLevel(first);
			}
		});
		int answer = 0;
		for (int i : order) {
			flow[i] = add[i];
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				flow[i] += flow[graph.destination(j)];
			}
			flow[i] -= remove[i];
			answer = Math.max(answer, flow[i]);
			flow[i] -= remove[i];
		}
		out.printLine(answer);
	}
}
