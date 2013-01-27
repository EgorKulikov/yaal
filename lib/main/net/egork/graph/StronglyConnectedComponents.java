package net.egork.graph;

import net.egork.collections.set.EHashSet;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class StronglyConnectedComponents<V> {
	private final Graph<V> graph;
	private int[] order;
	private boolean[] visited;
	private int index = 0;
	private int vertexCount;
	private int[] condensed;
	private Set<V> current;
	private Set<Integer> next;
	private Graph<Set<V>> result;

	private StronglyConnectedComponents(Graph<V> graph) {
		this.graph = graph;
		vertexCount = graph.getVertexCount();
		order = new int[vertexCount];
		visited = new boolean[vertexCount];
		condensed = new int[vertexCount];
	}

	public static<V> Graph<Set<V>> kosaraju(Graph<V> graph) {
		return new StronglyConnectedComponents<V>(graph).kosaraju();
	}

	private Graph<Set<V>> kosaraju() {
		for (int i = 0; i < vertexCount; i++) {
			if (!visited[i])
				firstDFS(i);
		}
		Arrays.fill(visited, false);
		result = new Graph<Set<V>>();
		index = 0;
		for (int i = vertexCount - 1; i >= 0; i--) {
			if (!visited[order[i]]) {
				current = new EHashSet<V>();
				next = new EHashSet<Integer>();
				secondDFS(order[i]);
				result.addVertex(current);
				for (int set : next)
					result.addEdge(set, index, 1, 0, -1);
				index++;
			}
		}
		return result;
	}

	private void secondDFS(int vertexID) {
		if (visited[vertexID]) {
			if (condensed[vertexID] != index)
				next.add(condensed[vertexID]);
			return;
		}
		current.add(graph.vertices[vertexID]);
		condensed[vertexID] = index;
		visited[vertexID] = true;
		int edgeID = graph.firstInbound[vertexID];
		while (edgeID != -1) {
			if (graph.removed[edgeID]) {
				edgeID = graph.nextInbound[edgeID];
				continue;
			}
			secondDFS(graph.from[edgeID]);
			edgeID = graph.nextInbound[edgeID];
		}
	}

	private void firstDFS(int vertexID) {
		if (visited[vertexID])
			return;
		visited[vertexID] = true;
		int edgeID = graph.firstOutbound[vertexID];
		while (edgeID != -1) {
			if (graph.removed[edgeID]) {
				edgeID = graph.nextOutbound[edgeID];
				continue;
			}
			firstDFS(graph.to[edgeID]);
			edgeID = graph.nextOutbound[edgeID];
		}
		order[index++] = vertexID;
	}
}
