package net.egork.graph.edge.simpleedge;

import net.egork.graph.edge.Edge;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SimpleEdge implements Edge {
	private final int source;
	private final int destination;

	public SimpleEdge(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}

	public long getWeight() {
		return 1;
	}

	public long getCapacity() {
		return 0;
	}

	public long getFlow() {
		return 0;
	}

	public void pushFlow(long flow) {
		throw new UnsupportedOperationException();
	}

	public Edge getTransposedEdge() {
		return new SimpleTransposedEdge();
	}

	public Edge getReverseEdge() {
		return null;
	}

	protected class SimpleTransposedEdge implements Edge {
		public int getSource() {
			return destination;
		}

		public int getDestination() {
			return source;
		}

		public long getWeight() {
			return 1;
		}

		public long getCapacity() {
			return 0;
		}

		public long getFlow() {
			return 0;
		}

		public void pushFlow(long flow) {
			throw new UnsupportedOperationException();
		}

		public Edge getTransposedEdge() {
			return SimpleEdge.this;
		}

		public Edge getReverseEdge() {
			return null;
		}
	}
}
