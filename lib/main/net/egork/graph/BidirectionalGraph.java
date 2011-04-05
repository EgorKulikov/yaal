package net.egork.graph;

import net.egork.graph.Graph;
import net.egork.graph.Edge;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class BidirectionalGraph extends Graph {
	public BidirectionalGraph(int size) {
		super(size);
	}

	@Override
	public void addEdge(Edge edge) {
		super.addEdge(edge);
		super.addEdge(edge.getTransposedEdge());
	}
}
