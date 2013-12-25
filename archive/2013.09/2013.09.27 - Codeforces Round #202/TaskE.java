package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	int maxInterrupt = -1;
	int variants = 0;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int monasteryCount = in.readInt();
		boolean[] isMonastery = new boolean[count];
		int monasterySample = -1;
		for (int i = 0; i < monasteryCount; i++)
			isMonastery[monasterySample = in.readInt() - 1] = true;
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		int[] length = new int[count - 1];
		IOUtils.readIntArrays(in, from, to, length);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = new BidirectionalGraph(count, count - 1);
		for (int i = 0; i < count - 1; i++)
			graph.addWeightedEdge(from[i], to[i], length[i]);
		int[] between = new int[2];
		for (int i = 0; i < 2; i++) {
			Pair<long[],int[]> pair = ShortestDistance.dijkstraAlgorithm(graph, monasterySample);
			long distance = 0;
			for (int j = 0; j < count; j++) {
				if (isMonastery[j] && pair.first[j] > distance) {
					between[i] = j;
					monasterySample = j;
					distance = pair.first[j];
				}
			}
		}
		for (int i1 = 0; i1 < between.length; i1++) {
			int i = between[i1];
			Pair<long[], int[]> pair = ShortestDistance.dijkstraAlgorithm(graph, i);
			int[] qty = new int[count];
			int[] qtySuper = new int[count];
			int current = between[0] + between[1] - i;
			long[] distanceToEnd = ShortestDistance.dijkstraAlgorithm(graph, current).first;
			int leftCount = 0;
			int rightCount = 0;
			for (int j = 0; j < count; j++) {
				if (isMonastery[j]) {
					if (distanceToEnd[j] > pair.first[j])
						leftCount++;
					else
						rightCount++;
				}
			}
			long distance = pair.first[current];
			int totalSuper = -1;
			for (int j = 0; j < count; j++) {
				if (isMonastery[j] && distance == pair.first[j]) {
					qtySuper[j] = 1;
					totalSuper++;
				}
			}
			qtySuper[current] = 0;
			boolean[] visited = new boolean[count];
			calculate(i, -1, qty, qtySuper, isMonastery, graph);
			while (true) {
				if (pair.first[current] * 2 < distance || i1 == 1 && pair.first[current] * 2 == distance)
					break;
				int remainingSuper = totalSuper - qtySuper[current];
				if (remainingSuper == 0)
					leftCount = 0;
				if (!isMonastery[current]) {
					int candidate = monasteryCount - (rightCount - qty[current]) - leftCount;
					update(candidate);
				}
				visited[current] = true;
				for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
					int next = graph.destination(j);
					if (visited[next] || next == graph.source(pair.second[current]))
						continue;
					go(next, visited, graph, qty, isMonastery);
				}
				current = graph.source(pair.second[current]);
			}
		}
		out.printLine(maxInterrupt, variants);
    }

	final void go(int current, boolean[] visited, Graph graph, int[] qty, boolean[] monastery) {
		if (visited[current])
			return;
		visited[current] = true;
		if (!monastery[current])
			update(qty[current]);
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i))
			go(graph.destination(i), visited, graph, qty, monastery);
	}

	private void update(int candidate) {
		if (maxInterrupt < candidate) {
			maxInterrupt = candidate;
			variants = 1;
		} else if (maxInterrupt == candidate)
			variants++;
	}

	private void calculate(int current, int last, int[] qty, int[] qtySuper, boolean[] isMonastery, Graph graph) {
		qty[current] = 0;
		if (isMonastery[current])
			qty[current] = 1;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last)
				continue;
			calculate(next, current, qty, qtySuper, isMonastery, graph);
			qty[current] += qty[next];
			qtySuper[current] += qtySuper[next];
		}
	}
}
