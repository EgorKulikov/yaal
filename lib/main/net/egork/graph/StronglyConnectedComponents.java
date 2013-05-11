package net.egork.graph;

import net.egork.collections.Pair;
import net.egork.collections.set.EHashSet;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class StronglyConnectedComponents<V> {
	private final Graph graph;
	private int[] order;
	private boolean[] visited;
	private int index = 0;
	private int vertexCount;
	private int[] condensed;
	private Set<Integer> next;

	private StronglyConnectedComponents(Graph graph) {
		this.graph = graph;
		vertexCount = graph.vertexCount();
		order = new int[vertexCount];
		visited = new boolean[vertexCount];
		condensed = new int[vertexCount];
	}

	public static Pair<int[], Graph> kosaraju(Graph graph) {
		return new StronglyConnectedComponents(graph).kosaraju();
	}

	private Pair<int[], Graph> kosaraju() {
		for (int i = 0; i < vertexCount; i++) {
			if (!visited[i])
				firstDFS(i);
		}
		Arrays.fill(visited, false);
		Graph result = new Graph(0);
		index = 0;
		for (int i = vertexCount - 1; i >= 0; i--) {
			if (!visited[order[i]]) {
				next = new EHashSet<Integer>();
				secondDFS(order[i]);
				result.addVertices(1);
				for (int set : next)
					result.addSimpleEdge(set, index);
				index++;
			}
		}
		return Pair.makePair(condensed, result);
	}

	private void secondDFS(int vertexID) {
		if (visited[vertexID]) {
			if (condensed[vertexID] != index)
				next.add(condensed[vertexID]);
			return;
		}
		condensed[vertexID] = index;
		visited[vertexID] = true;
		int edgeID = graph.firstInbound(vertexID);
		while (edgeID != -1) {
			secondDFS(graph.source(edgeID));
			edgeID = graph.nextInbound(edgeID);
		}
	}

	private void firstDFS(int vertexID) {
		if (visited[vertexID])
			return;
		visited[vertexID] = true;
		int edgeID = graph.firstOutbound(vertexID);
		while (edgeID != -1) {
			firstDFS(graph.destination(edgeID));
			edgeID = graph.nextOutbound(edgeID);
		}
		order[index++] = vertexID;
	}
}
