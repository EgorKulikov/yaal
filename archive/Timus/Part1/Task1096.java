package Timus.Part1;

import net.egork.collections.Pair;
import net.egork.collections.sequence.ListWrapper;
import net.egork.collections.sequence.WritableSequence;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1096 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int busCount = in.readInt();
		Pair<Long, Long>[] buses = Pair.readArray(busCount, in);
		long route = in.readInt();
		Pair<Long, Long> lastBus = Pair.readPair(in);
		Graph graph = new Graph(busCount + 1);
		for (int i = 0; i < busCount; i++) {
			for (int j = 0; j < busCount; j++) {
				if (i == j)
					continue;
				if (buses[i].first().equals(buses[j].first()) || buses[i].second().equals(buses[j].first())) {
					graph.add(new SimpleEdge(i, j));
				}
			}
			if (buses[i].first().equals(lastBus.first()) || buses[i].first().equals(lastBus.second())) {
				graph.add(new SimpleEdge(busCount, i));
			}
		}
		GraphAlgorithms.DistanceResult result = GraphAlgorithms.leviteAlgorithm(graph, busCount);
		int index = -1;
		long minLength = Long.MAX_VALUE;
		for (int i = 0; i < busCount; i++) {
			if ((route == buses[i].first() || route == buses[i].second()) && result.getDistances()[i] < minLength) {
				minLength = result.getDistances()[i];
				index = i;
			}
		}
		if (index == -1)
			out.println("IMPOSSIBLE");
		else {
			WritableSequence<Integer> path = ListWrapper.wrap(MiscUtils.getPath(result.getLast(), index)).subSequence(1);
			out.println(path.size());
			for (int vertex : path)
				out.println(vertex + 1);
		}
	}
}

