package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Edge {
	public int getSource();
	public int getDestination();
	public long getWeight();
	public long getCapacity();
	public long getFlow();
	public void pushFlow(long flow);
	public Edge getTransposedEdge();
	public Edge getReverseEdge();
}
