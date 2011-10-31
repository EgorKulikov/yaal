import net.egork.collections.sequence.Array;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int cityCount = in.readInt();
		int[] priority = IOUtils.readIntArray(in, cityCount);
		int[] from = new int[cityCount - 1];
		int[] to = new int[cityCount - 1];
		int[] capacity = new int[cityCount - 1];
		IOUtils.readIntArrays(in, from, to, capacity);
		for (int i = 0; i < cityCount - 1; i++) {
			from[i]--;
			to[i]--;
		}
		int[][] graph = GraphUtils.buildGraph(cityCount, from, to);
		int[] parent = new int[cityCount];
		int[] size = new int[cityCount];
		int[] capacityUp = new int[cityCount];
		capacityUp[0] = Integer.MAX_VALUE;
		calculate(graph, 0, -1, parent, size, from, to, capacityUp, capacity);
		int[][] remainingCapacity = new int[cityCount][];
		for (int i = 0; i < cityCount; i++) {
			remainingCapacity[i] = new int[size[i]];
			Arrays.fill(remainingCapacity[i], capacityUp[i]);
		}
		Integer[] order = SequenceUtils.order(Array.wrap(priority));
		int[] answer = new int[cityCount];
		for (int i : order) {
			int vertex = i;
			int day = 0;
			while (vertex != 0) {
				if (remainingCapacity[vertex][day] != 0) {
					remainingCapacity[vertex][day]--;
					vertex = parent[vertex];
				}
				day++;
			}
			answer[i] = day;
		}
		IOUtils.printArray(answer, out);
	}

	private int calculate(int[][] graph, int vertex, int last, int[] parent, int[] size, int[] from, int[] to, int[] capacityUp, int[] capacity) {
		parent[vertex] = last;
		size[vertex] = 1;
		for (int edge : graph[vertex]) {
			int nextVertex = GraphUtils.otherVertex(vertex, from[edge], to[edge]);
			if (nextVertex == last)
				continue;
			capacityUp[nextVertex] = capacity[edge];
			size[vertex] += calculate(graph, nextVertex, vertex, parent, size, from, to, capacityUp, capacity);
		}
		return size[vertex];
	}
}

