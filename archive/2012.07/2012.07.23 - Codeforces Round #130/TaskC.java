package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.graph.*;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		Graph<Integer> graph = new BidirectionalGraph<Integer>();
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			graph.add(new SimpleEdge<Integer>(from, to));
		}
		final Pair<Map<Integer,Long>,Map<Integer,Edge<Integer>>> firstDistance = GraphAlgorithms.dijkstraAlgorithm(graph, 1);
		final Pair<Map<Integer,Long>,Map<Integer,Edge<Integer>>> secondDistance = GraphAlgorithms.dijkstraAlgorithm(graph, count);
		double[] firstCount = getCount(count, graph, firstDistance, 1);
		double[] secondCount = getCount(count, graph, secondDistance, count);
		double answer = 1;
		for (int i = 2; i < count; i++) {
			if (firstDistance.first.get(i) + secondDistance.first.get(i) == firstDistance.first.get(count))
				answer = Math.max(answer, 2 * firstCount[i] * secondCount[i] / firstCount[count]);
		}
		out.printLine(answer);
	}

	private double[] getCount(int count, Graph<Integer> graph, final Pair<Map<Integer, Long>, Map<Integer, Edge<Integer>>> distance, int start) {
		double[] firstCount = new double[count + 1];
		firstCount[start] = 1;
		int[] order = new int[count];
		for (int i = 0; i < count; i++)
			order[i] = i + 1;
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(distance.first.get(first), distance.first.get(second));
			}
		});
		for (int i : order) {
			for (Edge<Integer> edge : graph.getIncident(i)) {
				if (distance.first.get(edge.getDestination()) + 1 == distance.first.get(i))
					firstCount[i] += firstCount[edge.getDestination()];
			}
		}
		return firstCount;
	}
}
