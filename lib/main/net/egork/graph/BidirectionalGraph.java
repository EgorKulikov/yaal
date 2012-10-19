package net.egork.graph;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class BidirectionalGraph<V> extends Graph<V> {
	public int[] transposedEdge;

	public BidirectionalGraph() {
		this(10);
	}

	public BidirectionalGraph(int vertexCapacity) {
		this(vertexCapacity, vertexCapacity);
	}

	public BidirectionalGraph(int vertexCapacity, int edgeCapacity) {
		super(vertexCapacity, 2 * edgeCapacity);
		transposedEdge = new int[2 * edgeCapacity];
	}

	@Override
	public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
		int lastEdgeCount = edgeCount;
		super.addEdge(fromID, toID, weight, capacity, reverseEdge);
		super.addEdge(toID, fromID, weight, capacity, reverseEdge);
		this.transposedEdge[lastEdgeCount] = lastEdgeCount + 1;
		this.transposedEdge[lastEdgeCount + 1] = lastEdgeCount;
		return lastEdgeCount;
	}

	@Override
	protected GraphEdge createEdge(int id) {
		return new BidirectionalGraphEdge(id);
	}

	@Override
	protected int entriesPerEdge() {
		return 2;
	}

	@Override
	protected void ensureEdgeCapacity(int size) {
		if (size > from.length) {
			super.ensureEdgeCapacity(size);
			transposedEdge = resize(transposedEdge, from.length);
		}
	}

	protected class BidirectionalGraphEdge extends GraphEdge {
		protected BidirectionalGraphEdge(int id) {
			super(id);
		}

		@Override
		public int getTransposedID() {
			return transposedEdge[id];
		}

		@Override
		public Edge<V> getTransposedEdge() {
			return edges[transposedEdge[id]];
		}
	}
}
