package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class BidirectionalGraph<V> extends Graph<V> {
	@Override
	public void add(Edge<V> edge) {
		super.add(edge);
		super.add(edge.getTransposedEdge());
	}

	@Override
	public void remove(Edge<V> edge) {
		super.remove(edge);
		super.remove(edge.getTransposedEdge());
	}
}
