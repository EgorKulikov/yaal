package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int boyCount = in.readInt();
		int[] boySkill = IOUtils.readIntArray(in, boyCount);
		int girlCount = in.readInt();
		int[] girlSkill = IOUtils.readIntArray(in, girlCount);
		Graph graph = new Graph(boyCount + girlCount + 2);
		for (int i = 0; i < boyCount; i++) {
			graph.addFlowEdge(boyCount + girlCount, i, 1);
			for (int j = 0; j < girlCount; j++) {
				if (Math.abs(boySkill[i] - girlSkill[j]) <= 1) {
					graph.addFlowEdge(i, boyCount + j, 1);
				}
			}
		}
		for (int i = 0; i < girlCount; i++) {
			graph.addFlowEdge(i + boyCount, boyCount + girlCount + 1, 1);
		}
		out.printLine(MaxFlow.dinic(graph, boyCount + girlCount, boyCount + girlCount + 1));
	}
}
