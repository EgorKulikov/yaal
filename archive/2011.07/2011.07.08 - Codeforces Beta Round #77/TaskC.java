import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int intersectionCount = in.readInt();
		int roadCount = in.readInt();
		int[] from = new int[roadCount];
		int[] to = new int[roadCount];
		int[] weight = new int[roadCount];
		int source = in.readInt() - 1;
		int destination = in.readInt() - 1;
		IOUtils.readIntArrays(in, from, to, weight);
		for (int i = 0; i < roadCount; i++) {
			from[i]--;
			to[i]--;
		}
		int[] maxDistance = new int[intersectionCount];
		int[] cost = new int[intersectionCount];
		IOUtils.readIntArrays(in, maxDistance, cost);
		int[][] graph = GraphUtils.buildGraph(intersectionCount, from, to);
		long[][] costGraph = new long[intersectionCount][intersectionCount];
		for (int i = 0; i < intersectionCount; i++) {
			final long[] distance = new long[intersectionCount];
			Arrays.fill(distance, Long.MAX_VALUE / 2);
			distance[i] = 0;
			NavigableSet<Integer> queue = new TreeSet<Integer>(new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					int value = IntegerUtils.longCompare(distance[o1], distance[o2]);
					if (value != 0)
						return value;
					return o1 - o2;
				}
			});
			queue.add(i);
			while (!queue.isEmpty()) {
				int vertex = queue.pollFirst();
				for (int edge : graph[vertex]) {
					int nextVertex = GraphUtils.otherVertex(vertex, from[edge], to[edge]);
					long nextDistance = distance[vertex] + weight[edge];
					if (nextDistance < distance[nextVertex]) {
						queue.remove(nextVertex);
						distance[nextVertex] = nextDistance;
						queue.add(nextVertex);
					}
				}
			}
			for (int j = 0; j < intersectionCount; j++) {
				if (maxDistance[i] >= distance[j])
					costGraph[i][j] = cost[i];
				else
					costGraph[i][j] = Long.MAX_VALUE / 2;
			}
		}
		long[] result = new long[intersectionCount];
		Arrays.fill(result, Long.MAX_VALUE / 2);
		result[source] = 0;
		boolean[] used = new boolean[intersectionCount];
		for (int i = 0; i < intersectionCount; i++) {
			int bestIndex = -1;
			long bestValue = Long.MAX_VALUE;
			for (int j = 0; j < intersectionCount; j++) {
				if (!used[j] && result[j] < bestValue) {
					bestValue = result[j];
					bestIndex = j;
				}
			}
			if (bestIndex == -1)
				break;
			used[bestIndex] = true;
			for (int j = 0; j < intersectionCount; j++)
				result[j] = Math.min(result[j], bestValue + costGraph[bestIndex][j]);
		}
		out.println(result[destination] == Long.MAX_VALUE / 2 ? -1 : result[destination]);
	}
}

