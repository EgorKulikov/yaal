package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class WeightedEdge<V> extends SimpleEdge<V> {
	private final long weight;

	public WeightedEdge(V source, V destination, long weight) {
		super(source, destination);
		this.weight = weight;
	}

	@Override
	public long getWeight() {
		return weight;
	}

	@Override
	public Edge<V> getTransposedEdge() {
		if (transposed == null)
			transposed = new WeightedTransposedEdge();
		return transposed;
	}

	protected class WeightedTransposedEdge extends SimpleTransposedEdge {
		@Override
		public long getWeight() {
			return weight;
		}
	}
}
