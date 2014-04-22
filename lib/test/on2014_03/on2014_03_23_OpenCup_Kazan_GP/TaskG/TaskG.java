package on2014_03.on2014_03_23_OpenCup_Kazan_GP.TaskG;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		int length = in.readInt();
		int[] order = IOUtils.readIntArray(in, length);
		MiscUtils.decreaseByOne(from, to, order);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		boolean[] traversed = new boolean[count - 1];
		int qty = 0;
		LCA lca = new LCA(graph);
		lca.getLCA(0, 0);
		int[] parent = new int[count];
		for (int i = 1; i < count; i++) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				if (lca.getLevel(graph.destination(j)) == lca.getLevel(i) - 1) {
					parent[i] = j;
					break;
				}
			}
		}
		int[] answer = new int[length];
		for (int j = 0; j < order.length; j++) {
			int i = order[j];
			int current = i;
			while (current != 0 && !traversed[parent[current] >> 1]) {
				traversed[parent[current] >> 1] = true;
				qty++;
				current = graph.destination(parent[current]);
			}
			answer[j] = 2 * qty - lca.getLevel(i);
		}
		out.printLine(answer);
    }
}
