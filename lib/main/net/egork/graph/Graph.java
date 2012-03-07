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

	public List<Edge> getIncident(int vertex) {
		return edges[vertex];
	}

	public void add(Edge edge) {
		edges[edge.getSource()].add(edge);
		edge = edge.getReverseEdge();
		if (edge != null)
			edges[edge.getSource()].add(edge);
	}

	public void removeEdge(Edge edge) {
		edges[edge.getSource()].remove(edge);
	}

	public void removeVertex(int vertex) {
		edges[vertex] = new ArrayList<Edge>();
	}
}
