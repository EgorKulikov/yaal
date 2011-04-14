package April2011.CodeforcesUkrainianSchoolOlympiad;

import net.egork.collections.IndependentSetSystem;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int cityCount = in.readInt();
		int roadCount = in.readInt();
		int goldCost = in.readInt();
		int silverCost = in.readInt();
		Edge[] edges = new Edge[roadCount];
		for (int i = 0; i < roadCount; i++) {
			int source = in.readInt() - 1;
			int destination = in.readInt() - 1;
			int gold = in.readInt();
			int silver = in.readInt();
			edges[i] = new Edge(source, destination, gold, silver);
		}
		Arrays.sort(edges, new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return o1.gold - o2.gold;
			}
		});
		long answer = Long.MAX_VALUE;
		NavigableMap<Integer, IndependentSetSystem> state = new TreeMap<Integer, IndependentSetSystem>();
		for (Edge edge : edges) {
			NavigableMap<Integer, IndependentSetSystem> head = state.headMap(edge.silver, true);
			IndependentSetSystem current;
			if (head.isEmpty())
				current = new IndependentSetSystem(cityCount);
			else
				current = new IndependentSetSystem(head.lastEntry().getValue());
			current.join(edge.source, edge.destination);
			if (current.getSetCount() == 1)
				answer = Math.min(answer, (long)edge.gold * goldCost + (long)edge.silver * silverCost);
			state.put(edge.silver, current);
			for (Map.Entry<Integer, IndependentSetSystem> entry : state.tailMap(edge.silver, false).entrySet()) {
				if (!entry.getValue().join(edge.source, edge.destination))
					break;
				if (entry.getValue().getSetCount() == 1)
					answer = Math.min(answer, (long)edge.gold * goldCost + (long)entry.getKey() * silverCost);
			}
		}
		out.println(answer == Long.MAX_VALUE ? -1 : answer);
	}

	private static class Edge {
		private final int source;
		private final int destination;
		private final int gold;
		private final int silver;

		private Edge(int source, int destination, int gold, int silver) {
			this.source = source;
			this.destination = destination;
			this.gold = gold;
			this.silver = silver;
		}
	}
}

