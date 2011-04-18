package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class FlowEdge extends SimpleEdge {
	private long capacity;
	private long flow = 0;
	private Edge reverseEdge;

	public FlowEdge(int source, int destination, long capacity) {
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
	public Edge getTransposedEdge() {
		return new FlowEdge(destination, source, capacity + flow);
	}

	@Override
	public Edge getReverseEdge() {
		return reverseEdge;
	}

	private class ReverseEdge implements Edge {
		public int getSource() {
			return destination;
		}

		public int getDestination() {
			return source;
		}

		public long getWeight() {
			return 0;
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

		public Edge getTransposedEdge() {
			throw new UnsupportedOperationException();
		}

		public Edge getReverseEdge() {
			return FlowEdge.this;
		}
	}
}
