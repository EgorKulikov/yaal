package on2015_09.on2015_09_19_RCC_2015________________.C___________________________________;


import net.egork.collections.intcollection.Heap;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] f = new int[m];
		int[] t = new int[m];
		IOUtils.readIntArrays(in, f, t);
		int[] order = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(f, t, order);
		Graph graph = Graph.createGraph(n, f, t);
		int[] top = GraphAlgorithms.topologicalSort(graph);
		int[] min = new int[n];
		int[] max = ArrayUtils.createArray(n, n - 1);
		for (int i = 0; i < n; i++) {
			if (order[i] != -1) {
				min[i] = order[i];
				max[i] = order[i];
			}
		}
		for (int i : top) {
			for (int j = graph.firstInbound(i); j != -1; j = graph.nextInbound(j)) {
				min[i] = Math.max(min[i], min[graph.source(j)] + 1);
			}
		}
		ArrayUtils.reverse(top);
		for (int i : top) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				max[i] = Math.min(max[i], max[graph.destination(j)] - 1);
			}
		}
		Heap heap = new Heap((a, b) -> max[a] - max[b], n);
		int[] byMin = ArrayUtils.order(min);
		int at = 0;
		int[] answer = new int[n];
		for (int i = 0; i < n; i++) {
			while (at < n && min[byMin[at]] == i) {
				heap.add(byMin[at++]);
			}
			answer[i] = heap.poll();
		}
		answer = ArrayUtils.reversePermutation(answer);
		for (int i = 0; i < n; i++) {
			answer[i]++;
		}
		out.printLine(answer);
	}
}
