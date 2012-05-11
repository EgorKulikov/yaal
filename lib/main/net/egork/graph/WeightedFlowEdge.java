package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class WeightedFlowEdge<V> extends FlowEdge<V> {
	private final long weight;

	public WeightedFlowEdge(V source, V destination, long weight, long capacity) {
		super(source, destination, capacity);
		this.weight = weight;
	}

	@Override
	public long getWeight() {
		return weight;
	}

	@Override
	public Edge<V> getTransposedEdge() {
		if (transposed == null)
			transposed = new WeightedFlowEdge<V>(destination, source, weight, getCapacity() + getFlow());
		return transposed;
	}
}
