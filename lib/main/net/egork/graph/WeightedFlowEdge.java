package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class WeightedFlowEdge extends FlowEdge {
	private final long weight;

	public WeightedFlowEdge(int source, int destination, long weight, long capacity) {
		super(source, destination, capacity);
		this.weight = weight;
	}

	@Override
	public long getWeight() {
		return weight;
	}

	@Override
	public Edge getTransposedEdge() {
		return new WeightedFlowEdge(destination, source, weight, getCapacity() + getFlow());
	}
}
