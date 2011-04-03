package net.egork.graph.edge.weightededge;

import net.egork.graph.edge.Edge;
import net.egork.graph.edge.simpleedge.SimpleEdge;

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
		return new WeightedTransposedEdge();
	}

	protected class WeightedTransposedEdge extends SimpleTransposedEdge {
		@Override
		public long getWeight() {
			return weight;
		}
	}
}
