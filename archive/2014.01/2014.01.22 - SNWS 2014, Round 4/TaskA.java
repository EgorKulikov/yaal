package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int start = in.readInt() - 1;
		int[] who = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = Graph.createGraph(count, from, to);
		int[] order = GraphAlgorithms.topologicalSort(graph);
		int[][] answer = new int[2][count];
		ArrayUtils.reverse(order);
		for (int i : order) {
			if (graph.firstOutbound(i) == -1) {
				answer[0][i] = 0;
				answer[1][i] = 1;
				continue;
			}
			answer[0][i] = 1 - who[i];
			answer[1][i] = 1 - who[i];
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				for (int k = 0; k < 2; k++) {
					if (answer[k][next] == who[i])
						answer[1 - k][i] = who[i];
				}
			}
		}
		out.printLine(answer[0][start]);
	}
}
