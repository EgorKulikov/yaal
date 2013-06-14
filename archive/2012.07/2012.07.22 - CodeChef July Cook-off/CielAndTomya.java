package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.graph.*;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class CielAndTomya {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, length);
		Graph<Integer> graph = new BidirectionalGraph<Integer>();
		int[][] distance = new int[count + 1][count + 1];
		ArrayUtils.fill(distance, Integer.MAX_VALUE / 2);
		for (int i = 0; i < edgeCount; i++) {
			graph.add(new WeightedEdge<Integer>(from[i], to[i], length[i]));
			distance[from[i]][to[i]] = distance[to[i]][from[i]] = length[i];
		}
		final Pair<Map<Integer,Long>,Map<Integer,Edge<Integer>>> result = GraphAlgorithms.dijkstraAlgorithm(graph, 1);
		int[] order = new int[count];
		for (int i = 0; i < count; i++)
			order[i] = i + 1;
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(result.first.get(first), result.first.get(second));
			}
		});
		int[] ways = new int[count + 1];
		ways[1] = 1;
		for (int i = 1; i < count; i++) {
			int current = order[i];
			for (int j = 0; j < i; j++) {
				if (result.first.get(order[j]) + distance[order[j]][current] == result.first.get(current))
					ways[current] += ways[order[j]];
			}
		}
		out.printLine(ways[count]);
	}
}
