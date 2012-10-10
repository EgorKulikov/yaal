package net.egork;

import net.egork.graph.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graphon {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		if (count == 0)
			throw new UnknownError();
		Graph<Integer> graph = new BidirectionalGraph<Integer>();
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			int length = in.readInt();
			graph.add(new WeightedEdge<Integer>(from, to, length));
		}
		int last = last = count + 99;
		Map<Integer, Long> distance = GraphAlgorithms.dijkstraAlgorithm(graph, last).first;
		int current = 100;
		List<Integer> answer = new ArrayList<Integer>();
		while (current != last) {
			int next = 1000;
			for (Edge<Integer> edge : graph.getIncident(current)) {
				if (edge.getDestination() < next && distance.get(edge.getDestination()) + edge.getWeight() == distance.get(current))
					next = edge.getDestination();
			}
			answer.add(current);
			current = next;
		}
		answer.add(last);
		out.printLine(answer.toArray());
	}
}
