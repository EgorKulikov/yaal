package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class WeightedEdge extends SimpleEdge {
	private final long weight;

	public WeightedEdge(int source, int destination, long weight) {
		super(source, destination);
		this.weight = weight;
	}

	@Override
	public long getWeight() {
		return weight;
	}

	@Override
	public Edge getTransposedEdge() {
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
