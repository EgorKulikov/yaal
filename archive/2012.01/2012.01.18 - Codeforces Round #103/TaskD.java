package net.egork;

import net.egork.graph.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int capital = in.readInt() - 1;
		Edge[] edges = new Edge[edgeCount];
		Graph graph = new BidirectionalGraph(vertexCount);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int length = in.readInt();
			graph.add(edges[i] = new WeightedEdge(from, to, length));
		}
		int rocketDistance = in.readInt();
		GraphAlgorithms.DistanceResult result = GraphAlgorithms.leviteAlgorithm(graph, capital);
		int answer = 0;
		for (int i = 0; i < vertexCount; i++) {
			if (result.distances[i] == rocketDistance)
				answer++;
		}
		for (Edge edge : edges) {
			if (result.distances[edge.getSource()] < rocketDistance && result.distances[edge.getDestination()] > rocketDistance ||
				result.distances[edge.getSource()] > rocketDistance && result.distances[edge.getDestination()] < rocketDistance)
			{
				answer++;
			} else if (result.distances[edge.getSource()] < rocketDistance || result.distances[edge.getDestination()] < rocketDistance) {
				long delta = 2 * rocketDistance - result.distances[edge.getSource()] - result.distances[edge.getDestination()];
				boolean bothSmaller = result.distances[edge.getSource()] < rocketDistance && result.distances[edge.getDestination()] < rocketDistance;
				if (delta < edge.getWeight())
					answer += bothSmaller ? 2 : 1;
				else if (delta == edge.getWeight())
					answer += bothSmaller ? 1 : 0;
			}
		}
		out.printLine(answer);
	}
}
