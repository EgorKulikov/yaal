package April2011.CodechefAprilLongContest;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.WeightedEdge;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class GeneralizedIndependentSets implements Solver {
	public static int[][] leviteAlgorithm(Graph graph, int source) {
		int size = graph.getSize();
		Deque<Integer> queue = new ArrayDeque<Integer>(size);
		boolean[] processed = new boolean[size];
		boolean[] notReached = new boolean[size];
		Arrays.fill(notReached, true);
		int[] distance = new int[size];
		int[] last = new int[size];
		Arrays.fill(distance, 1000);
		distance[source] = 0;
		last[source] = -1;
		queue.add(source);
		notReached[source] = false;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			processed[current] = true;
			for (Edge edge : graph.getIncident(current)) {
				int next = edge.getDestination();
				int weight = (int) edge.getWeight();
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = current;
					if (notReached[next]) {
						notReached[next] = false;
						queue.add(next);
					} else if (processed[next]) {
						processed[next] = false;
						queue.addFirst(next);
					}
				}
			}
		}
		return new int[][]{distance, last};
	}


	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] weights = new int[n];
		for (int i = 0; i < n; i++)
			weights[i] = (int) (in.readDouble() * 1000 + .5);
		int[] from = new int[m];
		int[] to = new int[m];
		in.readIntArrays(from, to);
		for (int i = 0; i < m; i++) {
			if (from[i] == to[i])
				continue;
			if (weights[from[i]] + weights[to[i]] > 1000) {
				out.println("Bad Cycle: " + from[i] + " " + to[i] + " -1");
				return;
			}
		}
		Graph graph = new BidirectionalGraph(2 * n);
		for (int i = 0; i < m; i++) {
			if (from[i] == to[i])
				continue;
			graph.add(new WeightedEdge(from[i], to[i] + n, 1000 - weights[from[i]] - weights[to[i]]));
			graph.add(new WeightedEdge(from[i] + n, to[i], 1000 - weights[from[i]] - weights[to[i]]));
		}
		for (int i = 0; i < n; i++) {
			int[][] result = leviteAlgorithm(graph, i);
			for (Edge edge : graph.getIncident(i + n)) {
				int destination = edge.getDestination();
				if (edge.getWeight() + result[0][destination] < 1000) {
					List<Integer> cycle = new ArrayList<Integer>();
					cycle.add(destination);
					boolean[] visited = new boolean[n];
					visited[destination] = true;
					boolean good = true;
					while (destination != i) {
						destination = result[1][destination];
						final int realDestination = destination % n;
						cycle.add(realDestination);
						if (visited[realDestination]) {
							good = false;
							break;
						}
						visited[realDestination] = true;
					}
					if (good) {
						out.print("Bad Cycle:");
						for (int vertex : cycle)
							out.print(" " + vertex);
						out.println(" -1");
						return;
					}
				}
			}
			graph.removeVertex(i);
			graph.removeVertex(i + n);
		}
		out.println("Ok");
	}
}

