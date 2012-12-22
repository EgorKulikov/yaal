package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1 {
	private int[][] graph;
	private boolean[] accounted;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		graph = new int[vertexCount][];
		for (int i = 0; i < vertexCount; i++) {
			int edgeCount = in.readInt();
			graph[i] = IOUtils.readIntArray(in, edgeCount);
		}
		MiscUtils.decreaseByOne(graph);
		if (vertexCount % 2 == 1) {
			out.printLine("NO");
			return;
		}
//		Graph graph = new Graph(vertexCount * 2 + 2);
//		for (int i = 0; i < vertexCount; i++) {
//			int edgeCount = in.readInt();
//			for (int j = 0; j < edgeCount; j++)
//				graph.add(new FlowEdge(i, in.readInt() - 1 + vertexCount, 1));
//		}
//		if (vertexCount % 2 == 1) {
//			out.printLine("NO");
//			return;
//		}
//		int source = 2 * vertexCount;
//		int sink = source + 1;
//		for (int i = 0; i < vertexCount; i++) {
//			graph.add(new FlowEdge(source, i, 1));
//			graph.add(new FlowEdge(vertexCount + i, sink, 1));
//		}
//		if (GraphAlgorithms.dinic(graph, source, sink) != vertexCount) {
//			out.printLine("NO");
//			return;
//		}
//		for (int i = 0; i < vertexCount; i++) {
//			int to = -1;
//			for (Edge edge : graph.getIncident(i)) {
//				if (edge instanceof FlowEdge && edge.getFlow() == 1) {
//					to = edge.getDestination();
//					break;
//				}
//			}
//			if (GraphAlgorithms.dinic(graph, i, to) != 0) {
//				out.printLine("NO");
//				return;
//			}
//		}
		accounted = new boolean[vertexCount];
		for (int i = 0; i < vertexCount / 2; i++) {
			used = accounted.clone();
			tin = new int[vertexCount];
			fup = new int[vertexCount];
			timer = 0;
			boolean good = false;
			for (int j = 0; j < vertexCount; j++) {
				if (used[j])
					continue;
				int[] result = dfs(j, -1);
				if (result != null) {
					good = true;
					accounted[result[0]] = true;
					accounted[result[1]] = true;
					break;
				}
			}
			if (!good) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}

	boolean[] used;
	int[] tin;
	int[] fup;
	int timer;

	int[] dfs(int vertex, int last) {
		used[vertex] = true;
		tin[vertex] = fup[vertex] = timer++;
		for (int to : graph[vertex]) {
			if (to == last || accounted[to]) continue;
			if (used[to]) {
				fup[vertex] = Math.min(fup[vertex], tin[to]);
			} else {
				int[] result = dfs(to, vertex);
				if (result != null)
					return result;
				fup[vertex] = Math.min(fup[vertex], fup[to]);
				if (fup[to] > tin[vertex]) {
					return new int[]{vertex, to};
				}
			}
		}
		return null;
	}
}
