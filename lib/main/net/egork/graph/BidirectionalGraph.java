package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class BidirectionalGraph extends Graph {
	public BidirectionalGraph(int size) {
		super(size);
	}

	@Override
	public void add(Edge edge) {
		super.add(edge);
		super.add(edge.getTransposedEdge());
	}

	@Override
	public void removeEdge(Edge edge) {
		super.removeEdge(edge);
		super.removeEdge(edge.getTransposedEdge());
	}
}
