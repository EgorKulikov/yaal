package net.egork.graph;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class GraphUtils {
	public static int[][] buildGraph(int vertexCount, int[] from, int[] to) {
		int edgeCount = from.length;
		int[] degree = new int[vertexCount];
		for (int i = 0; i < edgeCount; i++) {
			degree[from[i]]++;
			degree[to[i]]++;
		}
		int[][] graph = new int[vertexCount][];
		for (int i = 0; i < vertexCount; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < edgeCount; i++) {
			graph[from[i]][--degree[from[i]]] = i;
			graph[to[i]][--degree[to[i]]] = i;
		}
		return graph;
	}

	public static int[][] buildOrientedGraph(int vertexCount, int[] from, int[] to) {
		int edgeCount = from.length;
		int[] degree = new int[vertexCount];
		for (int i = 0; i < edgeCount; i++)
			degree[from[i]]++;
		int[][] graph = new int[vertexCount][];
		for (int i = 0; i < vertexCount; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < edgeCount; i++)
			graph[from[i]][--degree[from[i]]] = i;
		return graph;
	}

	public static int otherVertex(int vertex, int from, int to) {
		return from + to - vertex;
	}

	public static int[][] buildSimpleGraph(int vertexCount, int[] from, int[] to) {
		int[][] graph = buildGraph(vertexCount, from, to);
		simplifyGraph(from, to, graph);
		return graph;
	}

	public static int[][] buildSimpleOrientedGraph(int vertexCount, int[] from, int[] to) {
		int[][] graph = buildOrientedGraph(vertexCount, from, to);
		simplifyGraph(from, to, graph);
		return graph;
	}

	private static void simplifyGraph(int[] from, int[] to, int[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = otherVertex(i, from[graph[i][j]], to[graph[i][j]]);
			}
		}
	}
}
