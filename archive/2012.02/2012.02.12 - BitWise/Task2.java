package net.egork;

import net.egork.graph.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task2 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int houseCount = in.readInt();
		Graph graph = new Graph(count + houseCount + 2);
		int source = count + houseCount;
		int sink = source + 1;
		for (int i = 0; i < count; i++)
			graph.add(new FlowEdge(source, i, 1));
		for (int i = 0; i < houseCount; i++)
			graph.add(new WeightedFlowEdge(count + i, sink, 1, 1));
		int edgeCount = in.readInt();
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.add(new FlowEdge(from, count + to, 1));
		}
		long result = 0;
		int[] currentWeight = new int[houseCount];
		Arrays.fill(currentWeight, 1);
		for (int i = 0; i < count; i++) {
			result += GraphAlgorithms.minCostMaxFlow(graph, source, sink, 1).first;
			for (int j = 0; j < houseCount; j++) {
				boolean hasNonFull = false;
				for (Edge edge : graph.getIncident(count + j)) {
					if (edge instanceof WeightedFlowEdge && edge.getCapacity() != 0) {
						hasNonFull = true;
						break;
					}
				}
				if (!hasNonFull)
					graph.add(new WeightedFlowEdge(count + j, sink, ++currentWeight[j], 1));
			}
		}
		out.printLine(result);
	}
}
