package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheBlackRiders {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int hobbitCount = in.readInt();
		int holeCount = in.readInt();
		int required = in.readInt();
		int delta = in.readInt();
		int[][] time = IOUtils.readIntTable(in, hobbitCount, holeCount);
		String source = "source";
		String sink = "sink";
		String[] hobbit = new String[hobbitCount];
		for (int i = 0; i < hobbitCount; i++)
			hobbit[i] = "hobbit" + i;
		String[] hole = new String[holeCount];
		String[] holeToDig = new String[holeCount];
		for (int i = 0; i < holeCount; i++) {
			hole[i] = "hole" + i;
			holeToDig[i] = "holeToDig" + i;
		}
		int left = 0;
		int right = (int) 2e7;
		while (left < right) {
			int middle = (left + right) / 2;
			Graph<String> graph = new Graph<String>();
			for (int i = 0; i < hobbitCount; i++) {
				graph.addFlowEdge(source, hobbit[i], 1);
				for (int j = 0; j < holeCount; j++) {
					if (time[i][j] <= middle)
						graph.addFlowEdge(hobbit[i], hole[j], 1);
					if (time[i][j] + delta <= middle)
						graph.addFlowEdge(hobbit[i], holeToDig[j], 1);
				}
			}
			for (int i = 0; i < holeCount; i++) {
				graph.addFlowEdge(hole[i], sink, 1);
				graph.addFlowEdge(holeToDig[i], sink, 1);
			}
			if (MaxFlow.dinic(graph, source, sink) >= required)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
	}
}
