package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = new BidirectionalGraph(2 * count - 1, 2 * count - 2);
		for (int i = 0; i < count - 1; i++) {
			graph.addWeightedEdge(count + i, from[i], 1);
			graph.addWeightedEdge(count + i, to[i], 1);
		}
		int current = 0;
		Pair<long[],int[]> pair = null;
		for (int i = 0; i < 3; i++) {
			pair = ShortestDistance.dijkstraAlgorithm(graph, current);
			current = ArrayUtils.maxPosition(pair.first);
		}
		int distance = (int) pair.first[current] / 2;
		for (int i = 0; i < distance; i++)
			current = graph.source(pair.second[current]);
		long sum = 0;
		long answer = 0;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int curAnswer = go(graph.destination(i), current, distance - 1, graph);
			answer += sum * curAnswer;
			sum += curAnswer;
		}
		out.printLine(distance + 1, answer);
    }

	private int go(int current, int last, int distance, Graph graph) {
		if (distance == 0)
			return 1;
		int result = 0;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				result += go(next, current, distance - 1, graph);
		}
		return result;
	}
}
