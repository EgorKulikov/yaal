package net.egork.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Graph {
	private final int size;
	private final List<Edge>[] edges;

	public Graph(int size) {
		this.size = size;
		//noinspection unchecked
		edges = new List[size];
		for (int i = 0; i < size; i++)
			edges[i] = new ArrayList<Edge>();
	}

	public int getSize() {
		return size;
	}

	public Iterable<Edge> getIncident(int vertex) {
		return edges[vertex];
	}

	public void addEdge(Edge edge) {
		edges[edge.getSource()].add(edge);
		edge = edge.getReverseEdge();
		if (edge != null)
			edges[edge.getSource()].add(edge);
	}

	public void removeVertex(int vertex) {
		edges[vertex] = new ArrayList<Edge>();
	}
}
