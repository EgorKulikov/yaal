import net.egork.collections.ArrayUtils;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PruningTrees implements Solver {
	private int[][] graph;
	private int[] from, to, weight;
	private int radius;
	private int[][] result;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		radius = in.readInt();
		from = new int[vertexCount - 1];
		to = new int[vertexCount - 1];
		weight = new int[vertexCount - 1];
		IOUtils.readIntArrays(in, from, to, weight);
		graph = GraphUtils.buildGraph(vertexCount, from, to);
		result = new int[vertexCount][vertexCount];
		ArrayUtils.fill(result, -1);
		int answer = 0;
		for (int i = 0; i < vertexCount; i++)
			answer = Math.max(answer, goVertex(i, Collections.<Integer>emptySet(), -1));
		out.println(answer);
	}

	private int goVertex(int vertex, Set<Integer> mandatoryEdges, int mustRemove) {
		return processVertex(vertex, mandatoryEdges, mustRemove, radius, -1);
	}

	private int processVertex(int vertex, Set<Integer> mandatoryEdges, int mustRemove, int remaining, int cameFrom) {
		int result = 0;
		for (int edge : graph[vertex]) {
			int otherVertex = GraphUtils.otherVertex(vertex, from[edge], to[edge]);
			if (otherVertex == cameFrom)
				continue;
			if (mandatoryEdges.contains(edge))
				result += processVertex(otherVertex, mandatoryEdges, mustRemove, remaining - weight[edge], vertex) + weight[edge];
			else if (mustRemove != edge) {
				if (remaining >= weight[edge])
					result += Math.max(processVertex(otherVertex, mandatoryEdges, mustRemove, remaining - weight[edge], vertex) + weight[edge], goComponent(otherVertex, vertex, edge));
				else
					result += goComponent(otherVertex, vertex, edge);
			}
		}
		return result;
	}

	private int goComponent(int vertex, int cameFrom, int mustRemove) {
		if (result[vertex][cameFrom] != -1)
			return result[vertex][cameFrom];
		return result[vertex][cameFrom] = tryVertex(vertex, cameFrom, new HashSet<Integer>(), mustRemove, radius);
	}

	private int tryVertex(int vertex, int cameFrom, Set<Integer> mandatoryEdges, int mustRemove, int remaining) {
		int result = goVertex(vertex, mandatoryEdges, mustRemove);
		for (int edge : graph[vertex]) {
			int otherVertex = GraphUtils.otherVertex(vertex, from[edge], to[edge]);
			if (otherVertex == cameFrom)
				continue;
			if (weight[edge] <= remaining) {
				mandatoryEdges.add(edge);
				result = Math.max(result, tryVertex(otherVertex, vertex, mandatoryEdges, mustRemove, remaining - weight[edge]));
				mandatoryEdges.remove(edge);
			}
		}
		return result;
	}
}

