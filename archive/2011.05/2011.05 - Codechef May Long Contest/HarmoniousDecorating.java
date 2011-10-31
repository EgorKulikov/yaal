import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class HarmoniousDecorating implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		Pair<Integer, Integer>[] vertices = IOUtils.readIntPairArray(in, vertexCount);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		int[][] graph = GraphUtils.buildGraph(vertexCount, from, to);
		@SuppressWarnings({"unchecked"})
		Pair<Integer, Double>[][] edges = new Pair[vertexCount][];
		Comparator<Pair<Integer, Double>> comparator = new Comparator<Pair<Integer, Double>>() {
			public int compare(Pair<Integer, Double> first, Pair<Integer, Double> second) {
				return Double.compare(first.second, second.second);
			}
		};
		for (int i = 0; i < vertexCount; i++) {
			//noinspection unchecked
			edges[i] = new Pair[graph[i].length];
			for (int j = 0; j < graph[i].length; j++) {
				int otherVertex = GraphUtils.otherVertex(i, from[graph[i][j]], to[graph[i][j]]);
				edges[i][j] = Pair.makePair(graph[i][j], Math.atan2(vertices[otherVertex].second - vertices[i].second,
					vertices[otherVertex].first - vertices[i].first));
			}
			Arrays.sort(edges[i], comparator);
		}
		Set<Pair<Integer, Integer>> processed = new HashSet<Pair<Integer, Integer>>();
		List<Set<Integer>> faces = new ArrayList<Set<Integer>>();
		int outerIndex = -1;
		int minIndex = SequenceUtils.minIndex(Array.wrap(vertices));
		for (int i = 0; i < edgeCount; i++) {
			for (int j = 0; j < 2; j++) {
				int currentVertex = j == 0 ? from[i] : to[i];
				int startVertex = currentVertex;
				int lastEdge = i;
				if (processed.contains(Pair.makePair(currentVertex, lastEdge)))
					continue;
				Set<Integer> face = new HashSet<Integer>();
				do {
					int lastVertex = GraphUtils.otherVertex(currentVertex, from[lastEdge], to[lastEdge]);
					int index = Arrays.binarySearch(edges[currentVertex], Pair.makePair(lastEdge, Math.atan2(
						vertices[lastVertex].second - vertices[currentVertex].second,
						vertices[lastVertex].first - vertices[currentVertex].first)), comparator);
					index--;
					if (index < 0)
						index = edges[currentVertex].length - 1;
					lastEdge = edges[currentVertex][index].first;
					face.add(lastEdge);
					currentVertex = GraphUtils.otherVertex(currentVertex, from[lastEdge], to[lastEdge]);
					processed.add(Pair.makePair(currentVertex, lastEdge));
					if (currentVertex == minIndex && lastEdge == edges[currentVertex][0].first)
						outerIndex = faces.size();
				} while (currentVertex != startVertex);
				faces.add(face);
			}
		}
		int newVertexCount = faces.size();
		int[] newFrom = new int[edgeCount];
		int[] newTo = new int[edgeCount];
		Arrays.fill(newFrom, -1);
		Arrays.fill(newTo, -1);
		for (int i = 0; i < faces.size(); i++) {
			for (int edge : faces.get(i)) {
				if (newFrom[edge] == -1)
					newFrom[edge] = i;
				else
					newTo[edge] = i;
			}
		}
		int[][] newGraph = GraphUtils.buildGraph(newVertexCount, newFrom, newTo);
		int[] answer = new int[edgeCount];
		Arrays.fill(answer, 1);
		boolean[] visited = new boolean[newVertexCount];
		dfs(outerIndex, -1, newGraph, visited, answer, newFrom, newTo);
		IOUtils.printArray(answer, out);
	}

	private int dfs(int vertex, int edge, int[][] newGraph, boolean[] visited, int[] answer, int[] newFrom, int[] newTo) {
		int result = 1 - newGraph[vertex].length % 2;
		visited[vertex] = true;
		for (int nextEdge : newGraph[vertex]) {
			int nextVertex = GraphUtils.otherVertex(vertex, newFrom[nextEdge], newTo[nextEdge]);
			if (!visited[nextVertex])
				result = (result + dfs(nextVertex, nextEdge, newGraph, visited, answer, newFrom, newTo)) % 2;
		}
		if (result == 1 && edge != -1)
			answer[edge] = 0;
		return result;
	}
}

