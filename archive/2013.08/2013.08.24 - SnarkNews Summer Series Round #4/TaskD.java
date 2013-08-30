package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Graph graph = new Graph(count);
		for (int i = 1; i < count; i++)
			graph.addSimpleEdge(in.readInt(), i);
		int[] weight = IOUtils.readIntArray(in, count - 1);
		int[] order = GraphAlgorithms.topologicalSort(graph);
		ArrayUtils.reverse(order);
		int[] value = new int[count];
		long[] debug = new long[count];
		for (int i : order) {
			if (graph.firstOutbound(i) == -1) {
				value[i] = 1;
				debug[i] = 1;
			} else {
				for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
					int k = graph.destination(j);
					value[i] += value[k] * weight[j];
					debug[i] += debug[k] * weight[j];
				}
				value[i] &= 1;
				debug[i] %= MOD;
			}
		}
		if (value[0] == 0)
			out.printLine("Pass");
		else
			out.printLine(debug[0]);
    }
}
