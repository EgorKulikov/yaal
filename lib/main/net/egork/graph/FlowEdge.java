package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class FlowEdge<V> extends SimpleEdge<V> {
	private long capacity;
	private long flow = 0;
	private Edge<V> reverseEdge;

	public FlowEdge(V source, V destination, long capacity) {
		super(source, destination);
		this.capacity = capacity;
		reverseEdge = new ReverseEdge();
	}

	@Override
	public long getWeight() {
		return 0;
	}

	@Override
	public long getCapacity() {
		return capacity;
	}

	@Override
	public long getFlow() {
		return flow;
	}

	@Override
	public void pushFlow(long flow) {
		if (flow > 0) {
			if (this.capacity < flow)
				throw new IllegalArgumentException();
		} else {
			if (this.flow < -flow)
				throw new IllegalArgumentException();
		}
		capacity -= flow;
		this.flow += flow;
	}

	@Override
	public Edge<V> getTransposedEdge() {
		if (transposed == null)
			transposed = new FlowEdge<V>(destination, source, capacity + flow);
		return transposed;
	}

	@Override
	public Edge<V> getReverseEdge() {
		return reverseEdge;
	}

	private class ReverseEdge implements Edge<V> {
		public V getSource() {
			return destination;
		}

		public V getDestination() {
			return source;
		}

		public long getWeight() {
			return -FlowEdge.this.getWeight();
		}

		public long getCapacity() {
			return flow;
		}

		public long getFlow() {
			return capacity;
		}

		public void pushFlow(long flow) {
			FlowEdge.this.pushFlow(-flow);
		}

		public Edge<V> getTransposedEdge() {
			throw new UnsupportedOperationException();
		}

		public Edge<V> getReverseEdge() {
			return FlowEdge.this;
		}

		public void setMarked(boolean marked) {
			FlowEdge.this.setMarked(marked);
		}

		public boolean isMarked() {
			return FlowEdge.this.isMarked();
		}
	}
}
