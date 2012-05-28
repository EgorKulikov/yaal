package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SimpleEdge<V> implements Edge<V> {
	protected final V source;
	protected final V destination;
	protected boolean marked;
	protected Edge<V> transposed = null;

	public SimpleEdge(V source, V destination) {
		this.source = source;
		this.destination = destination;
	}

	public V getSource() {
		return source;
	}

	public V getDestination() {
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

	public Edge<V> getTransposedEdge() {
		if (transposed == null)
			transposed = new SimpleTransposedEdge();
		return transposed;
	}

	public Edge<V> getReverseEdge() {
		return null;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	protected class SimpleTransposedEdge implements Edge<V> {
		public V getSource() {
			return destination;
		}

		public V getDestination() {
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

		public Edge<V> getTransposedEdge() {
			return SimpleEdge.this;
		}

		public Edge<V> getReverseEdge() {
			return null;
		}

		public void setMarked(boolean marked) {
			SimpleEdge.this.setMarked(marked);
		}

		public boolean isMarked() {
			return SimpleEdge.this.isMarked();
		}
	}
}
